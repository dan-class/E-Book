import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Monitor de Sistema para Linux
 * Este programa exibe informações sobre o estado atual do sistema:
 * - Tamanho e uso das partições
 * - Memória total, usada e disponível
 * - Uso da CPU
 * - Top 5 processos consumindo mais CPU
 */
public class LinuxSystemMonitor {

    public static void main(String[] args) {
        try {
            // Exibe cabeçalho com data e hora
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataHora = formatter.format(new Date());
            System.out.println("=== MONITOR DE SISTEMA LINUX ===");
            System.out.println("Data/Hora: " + dataHora);
            System.out.println("==============================\n");

            // Exibe informações do sistema
            exibirInformacoesSistema();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exibe as principais informações do sistema
     */
    private static void exibirInformacoesSistema() throws Exception {
        System.out.println("=== INFORMAÇÕES DAS PARTIÇÕES ===");
        executarComando("df -h");

        System.out.println("\n=== USO DE MEMÓRIA ===");
        executarComando("free -h");

        System.out.println("\n=== USO DE CPU ===");
        executarComando("top -bn1 | grep '%Cpu'");

        System.out.println("\n=== TOP 5 PROCESSOS (CPU) ===");
        executarComando("ps -eo %cpu,%mem,pid,user,wchan:14,comm | sort -k 1 -r | head -6");

        System.out.println("\n=== VERSÃO DO KERNEL ===");
        executarComando("uname -r");

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
        processo.waitFor();  // Aguarda o término do processo
    }
}
