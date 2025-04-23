public class Jogo {
    private int scoreJogador = 0;
    private int scoreComputador = 0;
    private boolean emAndamento = false;
    private int[] posicao = {
        0, 3, 0, 3, 0, 
        2, 2, 2, 2, 2,
        0, 3, 0, 3, 0, 
        2, 2, 2, 2, 2,
        0, 3, 0, 3, 0
    };

    public boolean estaEmAndamento() {
        return emAndamento;
    }

    public void iniciar() {
        emAndamento = true;
    }

    public void encerrar() {
        emAndamento = false;
    }

    public int[] retornaPosicao() {
        return posicao;
    }

    public int retornaScoreJogador() {
        return scoreJogador;
    }

    public int retornaScoreComputador() {
        return scoreComputador;
    }

    public void verificaPosicao(String posicaoJogador) {
        for (int i = 0; i < posicao.length; i++) {

            int novaPosicaoJogador = 0;

            switch (posicaoJogador) {
                case "1":
                    novaPosicaoJogador = 0;
                    break;
                case "2":
                    novaPosicaoJogador = 2;
                    break;
                case "3":
                    novaPosicaoJogador = 4;
                    break;
                case "4":
                    novaPosicaoJogador = 10;
                    break;
                case "5":
                    novaPosicaoJogador = 12;
                    break;
                case "6":
                    novaPosicaoJogador = 14;
                    break;
                case "7":
                    novaPosicaoJogador = 20;
                    break;
                case "8":
                    novaPosicaoJogador = 22;
                    break;
                case "9":
                    novaPosicaoJogador = 24;
                    break;
                default:
                    break;
            }

            if (posicao[novaPosicaoJogador] == 0) {
                posicao[novaPosicaoJogador] = 4;
            } else {
                System.out.println(
                    TerminalColors.RED +
                    "Posição já está oculpada, escolha outra posição." +
                    TerminalColors.RESET);
            }
        }
    }

    public void mostraMapaJogo() {
        for (int i = 0; i < posicao.length; i++) {
            if (i == 0 || i == 5 || i == 10 || i == 15 || i == 20) {
                System.out.print("\t");
            }

            if (posicao[i] == 0) {
                System.out.print(" ");
            } else if (posicao[i] == 1) {
                System.out.print(
                    TerminalColors.RED +
                    TerminalColors.BOLD +
                    "X" +
                    TerminalColors.RESET
                );
            } else if (posicao[i] == 2) {
                System.out.print(
                    TerminalColors.YELLOW +
                    TerminalColors.BOLD +
                    "-" +
                    TerminalColors.RESET
                );
            } else if (posicao[i] == 3) {
                System.out.print(
                    TerminalColors.YELLOW +
                    TerminalColors.BOLD +
                    "|" +
                    TerminalColors.RESET
                );
            } else if (posicao[i] == 4) {
                System.out.print(
                    TerminalColors.CYAN +
                    TerminalColors.BOLD +
                    "O" +
                    TerminalColors.RESET
                );
            }
            
            if (i == 4 || i == 9 || i == 14 || i == 19 || i == 24) {
                System.out.println("");
            }
        }
    }

    public void escolhePosicaoComputador() {
        // TODO: fazer a lógica de escolha da posição do computador
    }

}
