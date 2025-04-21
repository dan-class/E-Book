import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Monitor de Sistemap para Linux (Versão Aprimorada)
 * Este programa exibe informações sobre o estado atual do sistema
 * e permite atualizações periódicas e filtragem de informações.
 */
public class LinuxSystemMonitorPlus {
    
    private static final int INTERVALO_PADRAO = 3; // Intervalo em segundos
    private static boolean executando = true;
    private static boolean mostrarParticoes = true;
    private static boolean mostrarMemoria = true;
    private static boolean mostrarCPU = true;
    private static boolean mostrarProcessos = true;
    private static int intervalo = INTERVALO_PADRAO;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MONITOR DE SISTEMA LINUX (APRIMORADO) ===");
        System.out.println("Pressione 'q' a qualquer momento para sair");
        System.out.println("Pressione 'd' para alternar a exibição de disco");
        System.out.println("Pressione 'm' para alternar a exibição de memória");
        System.out.println("Pressione 'c' para alternar a exibição de CPU");
        System.out.println("Pressione 'p' para alternar a exibição de processos");
        System.out.println("Pressione '+' ou '-' para ajustar o intervalo de atualização");
        System.out.println("===============================================\n");

        System.out.println("--> Digite o nome de um usuário para listar os processos: ");
        String usuario = scanner.nextLine();

        // Thread para monitorar comandos do usuário
        Thread comandosThread = new Thread(() -> {
            while (executando) {
                try {
                    if (System.in.available() > 0) {
                        char comando = (char) System.in.read();
                        switch (comando) {
                            case 'q':
                                executando = false;
                                System.out.println("\nEncerrando...");
                                break;
                            case 'd':
                                mostrarParticoes = !mostrarParticoes;
                                break;
                            case 'm':
                                mostrarMemoria = !mostrarMemoria;
                                break;
                            case 'c':
                                mostrarCPU = !mostrarCPU;
                                break;
                            case 'p':
                                mostrarProcessos = !mostrarProcessos;
                                break;
                            case '+':
                                intervalo = Math.min(intervalo + 1, 10);
                                System.out.println("\nIntervalo ajustado para " + intervalo + " segundos");
                                break;
                            case '-':
                                intervalo = Math.max(intervalo - 1, 1);
                                System.out.println("\nIntervalo ajustado para " + intervalo + " segundos");
                                break;
                        }
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        comandosThread.start();

        // Loop principal de atualização
        while (executando) {
            try {
                // Limpa a tela (apenas no terminal Linux)
                System.out.print("\033[H\033[2J");
                System.out.flush();

                //Exibe data e hora atuais
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String dataHora = formatter.format(new Date());
                System.out.println("=== MONITOR DE SISTEMA LINUX ===");
                System.out.println("Data/Hora: " + dataHora);
                System.out.println("Intervalo de atualização: " + intervalo + " segundos");
                System.out.println("===============================\n");

                // Exibe informações conforme configuração
                if (mostrarParticoes) {
                    System.out.println("=== INFORMAÇÕES DAS PARTIÇÕES ===");
                    executarComando("df -h");
                    System.out.println();

                }

                if (mostrarMemoria) {
                    System.out.println("=== USO DE MEMÓRIA ===");
                    executarComando("free -h");
                    System.out.println();
                }

                if (mostrarCPU) {
                    System.out.println("=== USO DE CPU ===");
                    executarComando("top -bn1 | grep '%Cpu'");
                    System.out.println();
                }

                if (mostrarProcessos) {
                    System.out.println("=== TOP 5 PROCESSOS (CPU) ===");
                    String cmd = "ps -u " + usuario + " -o %cpu,%mem,pid,user,wchan:14,comm | sort -k 1 -r | head -6";
                    executarComando(cmd);
                    System.out.println();
                }

                // Aguarda pelo intervalo de atualização
                Thread.sleep(intervalo * 1000);
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
                try {
                    Thread.sleep(5000); // Aguarda 5 segundos em caso de erro
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        scanner.close();
        System.exit(0);
    }

    /**
     * Executa um comando no sistema e exibe sua saída
     * @param comando O comando a ser executado
     */
    private static void executarComando(String comando) throws Exception {
        Process processo = Runtime.getRuntime().exec(new String[]{"bash", "-c", comando});

        BufferedReader leitor = new BufferedReader(
            new InputStreamReader(processo.getInputStream())
        );

        String linha;
        while ((linha = leitor.readLine()) != null) {
            System.out.println(linha);
        }

        leitor.close();
        processo.waitFor(); // Aguarda o término do processo
    }
}
