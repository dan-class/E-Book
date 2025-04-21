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
        }
    }

    public static void mostraMenu(Jogo jogo) {
        System.out.println(TerminalColors.YELLOW + "#################" + TerminalColors.RESET);
        System.out.println(TerminalColors.YELLOW + "# JOGO DA VELHA #" + TerminalColors.RESET);
        System.out.println(TerminalColors.YELLOW + "#################" + TerminalColors.RESET);

        if (!jogo.estaEmAndamento()) {
            System.out.println(TerminalColors.YELLOW + " 1 - Iniciar\t2 - Sair" + TerminalColors.RESET);
        } else {
            mostraMapa(jogo);
        }
    }

    public static void mostraMapa(Jogo jogo) {
        System.out.print(TerminalColors.YELLOW + TerminalColors.BOLD + "Posições:\t" + TerminalColors.RESET);
        System.out.println(
            TerminalColors.YELLOW + "[ " +
            TerminalColors.MAGENTA + "1, 2, 3, 4, 5, 6, 7, 8, 9" +
            TerminalColors.YELLOW + " ]" + TerminalColors.RESET);
        
        System.out.print(TerminalColors.YELLOW + "Mapa:\t" + TerminalColors.RESET);
        int[] posicoes = jogo.retornaPosicao();
        for (int i = 0; i < posicoes.length; i++) {
            System.out.println(posicoes[i]);
        }
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}