import java.util.Scanner;

public class JokenpoMelhorado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo();

        while (jogo.estaEmAndamento()) {
            limparTela();
            mostrarMenu(jogo);

            String entrada = scanner.nextLine();
            try {
                int jogadaJogador = Integer.parseInt(entrada);
                if (jogadaJogador < 1 || jogadaJogador > 3) throw new NumberFormatException();

                int jogadaPC = jogo.jogadaComputador();
                int resultado = jogo.compararJogada(jogadaJogador, jogadaPC);

                mostrarResultado(jogadaJogador, jogadaPC, resultado);

                if (jogo.getScoreJogador() >= 3) {
                    System.out.println(
                        TerminalColors.BG_WHITE +
                        TerminalColors.BOLD +
                        TerminalColors.BLUE +
                        "JOGADOR WIN!" + TerminalColors.RESET
                    );
                    jogo.encerrar();
                } else if (jogo.getScoreComputador() >= 3) {
                    System.out.println(
                        TerminalColors.BG_RED +
                        TerminalColors.BOLD +
                        TerminalColors.WHITE +
                        "Computador WIN!" + TerminalColors.RESET
                    );
                    mostraResultadoFinal(jogo);
                    jogo.encerrar();
                }

                Thread.sleep(3000);
            } catch (NumberFormatException e) {
                System.out.println(TerminalColors.BOLD + TerminalColors.RED + "Opção inválida!" + TerminalColors.RESET);
                esperar(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
        System.exit(0);
    }

    public static void mostrarMenu(Jogo jogo) {
        System.out.println(TerminalColors.BOLD + TerminalColors.YELLOW + "PEDRA, PAPEL E TESOURA!" + TerminalColors.RESET);
        System.out.println(TerminalColors.MAGENTA + "Escolha conforme abaixo:" + TerminalColors.RESET);
        System.out.print(TerminalColors.BOLD + TerminalColors.CYAN + "1" + TerminalColors.RESET);
        System.out.println(TerminalColors.MAGENTA + " - Pedra 🤛" + TerminalColors.RESET);
        System.out.print(TerminalColors.BOLD + TerminalColors.CYAN + "2" + TerminalColors.RESET);
        System.out.println(TerminalColors.MAGENTA + " - Papel 🫲" + TerminalColors.RESET);
        System.out.print(TerminalColors.BOLD + TerminalColors.CYAN + "3" + TerminalColors.RESET);
        System.out.println(TerminalColors.MAGENTA + " - Tesoura ✌️" + TerminalColors.RESET);
        System.out.print(
            TerminalColors.BOLD +
            TerminalColors.YELLOW +
            "Placar:\n\tJOGADOR - [" +
            TerminalColors.BG_MAGENTA +
            TerminalColors.CYAN +
            jogo.getScoreJogador() +
            TerminalColors.RESET +
            TerminalColors.YELLOW + "]" + " | " + "[" +
            TerminalColors.RESET +
            TerminalColors.BG_MAGENTA +
            TerminalColors.CYAN +
            jogo.getScoreComputador() +
            TerminalColors.RESET +
            TerminalColors.YELLOW + "]" +
            " - Computador" + TerminalColors.RESET +
            "\n--> ");
    }

    public static void mostraResultadoFinal(Jogo jogo) {
        System.out.print(
            TerminalColors.BOLD +
            TerminalColors.YELLOW +
            "PLACAR FINAL:\n\tJOGADOR - [" +
            TerminalColors.BG_MAGENTA +
            TerminalColors.CYAN +
            jogo.getScoreJogador() +
            TerminalColors.RESET +
            TerminalColors.YELLOW + "]" + " | " + "[" +
            TerminalColors.RESET +
            TerminalColors.BG_MAGENTA +
            TerminalColors.CYAN +
            jogo.getScoreComputador() +
            TerminalColors.RESET +
            TerminalColors.YELLOW + "]" +
            " - Computador" + TerminalColors.RESET +
            "\n--> ");
    }

    public static void mostrarResultado(int jogadaJogador, int jogadaPC, int resultado) {
        String[] listaOpcoes = {"Pedra 🤛", "Papel 🫲", "Tesoura ✌️"};
        System.out.println(TerminalColors.YELLOW + "Escolha jogador: " + TerminalColors.CYAN + listaOpcoes[jogadaJogador - 1] + TerminalColors.RESET);
        System.out.println(TerminalColors.YELLOW + "Escolha Computador: " + TerminalColors.CYAN + listaOpcoes[jogadaPC - 1] + TerminalColors.RESET);

        if (resultado == 0) {
            System.out.println("Empate.");
        }
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
