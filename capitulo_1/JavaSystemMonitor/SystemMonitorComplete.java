import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Monitor de Sistema Completo para Linux
 * Combina monitoramento de CPUm, memória, disco e processos
 * com histórico e detecção de anomalias simples.
 */
public class SystemMonitorComplete {

    // Contantes de configuração
    private static final int INTERVALO_ATUALIZADO = 3; // segundos
    private static final int LIMITE_HISTORICO = 20; // número de entradas no histórico
    private static final int LIMITE_CPU_ALERTA = 80; // percentual
    private static final int LIMITE_MEM_ALERTA = 90; // percentual
    private static final int LIMITE_DISCO_ALERTA = 90; // percentual

    // Listas para armazenar histórico
    private static List<Double> historicoCPU = new ArrayList<>();
    private static List<Double> historicoMem = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== MONITOR DE SISTEMA COMPLETO ===");
        System.out.println("Pressione Ctrl+C para encerrar");
        System.out.println("===================================");

        while (true) {
            try {
                // Limpa o console
                limparConsole();

                // Exibe hora atual
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                System.out.println("Data e hora: " + sdf.format(new Date()));
                System.out.println();

                //Coleta e exibe informações do sistema
                monitorarSistema();

                // Aguarda intervalo de atualização
                Thread.sleep(INTERVALO_ATUALIZADO * 10000);
                // Limpa o console
                limparConsole();

            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    /**
     * Coleta e exibe todas as informações do sistema
     */
    private static void monitorarSistema() throws Exception {
        // 1. Informações de CPU
        System.out.println("=== USO DE CPU ===");
        String cpuInfo = executarComando("top -bn1 | grep '%Cpu'");
        System.out.println(cpuInfo);

        // Extrai o percentual de CPU usado (100 - idle)
        double cpuUsada = 100 - extrairCpuIdle(cpuInfo);
        historicoCPU.add(cpuUsada);
        if (historicoCPU.size() > LIMITE_HISTORICO) {
            historicoCPU.remove(0);
        }

        // Exibe gráfico de histórico
        exibirGraficoHistorico("CPU", historicoCPU, LIMITE_CPU_ALERTA);

        // 2. Informações de Memória
        System.out.println("\n=== USO DE MEMÓRIA ===");
        String memInfo = executarComando("free");
        System.out.println(memInfo);

        // Extrai o percentual de memória usada
        double memUsada = extrairMemoriaUsada(memInfo);
        historicoMem.add(memUsada);
        if (historicoMem.size() > LIMITE_HISTORICO) {
            historicoMem.remove(0);
        }

        // Exibe gráfico de histórico
        exibirGraficoHistorico("Memória", historicoMem, LIMITE_MEM_ALERTA);

        // 3. Informações de Disco
        System.out.println("\n=== USO DE DISCO ===");
        String discoInfo = executarComando("df -h | grep -v none");
        System.out.println(discoInfo);

        // 4. Processos mais ativos
        System.out.println("\n=== TOP 5 PROCESSOS (CPU) ===");
        System.out.println(executarComando("ps -eo %cpu,pid,user,wchan:14,comm | sort -k 1 -r | head -6 | sort -gr"));

        System.out.println("\n=== TOP 5 PROCESSOS (MEMÓRIA) ===");
        System.out.println(executarComando("ps -eo %mem,pid,user,wchan:14,comm | sort -k 1 -r | head -6 | sort -gr"));
    }

    /**
     * Executa um comando e retorna sua saída como string
     */
    private static String executarComando(String comando) throws Exception {
        Process processo = Runtime.getRuntime().exec(new String[]{"bash", "-c", comando});

        BufferedReader leitor = new BufferedReader(
            new InputStreamReader(processo.getInputStream())
        );

        StringBuilder saida = new StringBuilder();
        String linha;
        while ((linha = leitor.readLine()) != null) {
            saida.append(linha).append("\n");
        }

        leitor.close();
        processo.waitFor();

        return saida.toString();
    }
    /**
     * Extrai o percentual de CPU idle da saída do top
     */
    private static double extrairCpuIdle(String cpuInfo) {
        // extrai o valor "id" que representa idle CPU
        try {
            // formato típico: %Cpu(s): 5.9 us, 2.3 sy, 0.0 ni, 91.8 id, 0.0 wa, 0.0 hi, 0.0 si, 0.0 st
            int idIndex = cpuInfo.indexOf(" id");
            if (idIndex > 0) {
                // Retrocede até encontrar um número
                int startIndex = idIndex - 1;
                while (startIndex > 0 && (Character.isDigit(cpuInfo.charAt(startIndex)) | cpuInfo.charAt(startIndex) == '.')) {
                    startIndex--;
                }
                // Extrai o valor numérico
                String idleStr = cpuInfo.substring(startIndex + 1, idIndex);
                return Double.parseDouble(idleStr.trim());
            }
        } catch (Exception e) {
            System.err.println("Erro ao extrair CPU idle: " + e.getMessage());
        }
        return 0.0; // valor padrão em caso de erro
    }

    /**
     * Extrai o percentual de memória usada
     */
    private static double extrairMemoriaUsada(String memInfo) {
        try {
            String[] linhas = memInfo.split("\n");
            if (linhas.length > 1) {
                String[] partes = linhas[1].trim().split("\\s+");
                if (partes.length >= 3) {
                    long total = Long.parseLong(partes[1]);
                    long usado = Long.parseLong(partes[2]);
                    return (usado * 100.0) / total;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao extrair uso de memória: " + e.getMessage());
        }
        return 0.0; // Valor padrão em caso de erro
    }

    /**
     * Exibe um gráfico simples do histórico de valores
    */
    private static void exibirGraficoHistorico(String titulo, List<Double> historico, int limiteAlerta) {
        System.out.println("\nHitórico de " + titulo + " (últimas " + historico.size() + " leituras):");

        // Valor máximo para escala
        double valorAtual = historico.isEmpty() ? 0 : historico.get(historico.size() - 1);

        // Exibe o valor atual
        System.out.printf("Valor atual: %.1f%% ", valorAtual);
        if (valorAtual > limiteAlerta) {
            System.out.println("⚠️ ALERTA! Acima do limite de " + limiteAlerta + "%");
        } else {
            System.out.println("✅ Normal");
        }

        // Exibe o gráfico
        for (Double valor : historico) {
            int barras = (int) (valor / 5); // 1 barra para cada 5%
            StringBuilder grafico = new StringBuilder("[");

            for (int i = 0; i < 20; i++) {
                if (i < barras) {
                    if (valor > limiteAlerta) {
                        grafico.append("!"); // Marca de alerta
                    } else {
                        grafico.append("=");
                    }
                } else {
                    grafico.append(" ");
                }
            }

            grafico.append("] ").append(String.format("%.1f%%", valor));
            System.out.println(grafico);
        }
    }

    /**
     * Limpa o console
     */
    private static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}