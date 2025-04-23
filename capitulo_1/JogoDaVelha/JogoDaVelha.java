import java.util.Scanner;

public class JogoDaVelha {
    private static boolean foraDoJogo = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo();

        limparTela();
        mostraMenu(jogo);

        while (foraDoJogo) {
            String entrada = scanner.nextLine();
            if (entrada.equals("2")) {
                System.out.println("Saindo do jogo...");
                scanner.close();
                System.exit(0);
            } else {
                jogo.iniciar();
                foraDoJogo = false;
            }
        }

        while (jogo.estaEmAndamento()) {
            limparTela();
            mostraMenu(jogo);

            System.out.print(
                TerminalColors.YELLOW +
                "Informe sua posição: " +
                TerminalColors.CYAN +
                TerminalColors.BOLD);

            try {
                String posicaoJogador = scanner.nextLine().trim();
                System.out.println(TerminalColors.RESET);
                jogo.verificaPosicao(posicaoJogador);
            } catch (Exception e) {
                esperar(2000);
            }
        }
    }

    public static void mostraMenu(Jogo jogo) {
        System.out.println(TerminalColors.YELLOW + "#################" + TerminalColors.RESET);
        System.out.println(TerminalColors.YELLOW + "# JOGO DA VELHA #" + TerminalColors.RESET);
        System.out.println(TerminalColors.YELLOW + "#################" + TerminalColors.RESET);

        if (!jogo.estaEmAndamento()) {
            System.out.println(TerminalColors.YELLOW + " 1 - Iniciar\t2 - Sair" + TerminalColors.RESET);
        } else {
            mostraPosicao(jogo);
        }
    }

    public static void mostraPosicao(Jogo jogo) {
        System.out.println(TerminalColors.YELLOW + TerminalColors.BOLD + "Posições:" + TerminalColors.RESET);
        System.out.println(TerminalColors.YELLOW + "\t1|2|3");
        System.out.println("\t-----");
        System.out.println("\t4|5|6");
        System.out.println("\t-----");
        System.out.println("\t7|8|9" + TerminalColors.RESET);

        System.out.println(TerminalColors.MAGENTA + TerminalColors.BOLD + "JOGO:" + TerminalColors.RESET);

        jogo.mostraMapaJogo();
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }
}