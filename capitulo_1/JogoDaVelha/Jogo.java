import java.util.Random;

public class Jogo {
    private int scoreJogador = 0;
    private int scoreComputador = 0;
    private boolean emAndamento = false;
    private boolean jogadorWin = false;
    private boolean computadorWin = false;
    private boolean jogadorJogou = false;
    private final Random random = new Random();
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

    public boolean retornaJogadorJogou() {
        return jogadorJogou;
    }

    public void resetaVezJogador() {
        jogadorJogou = false;
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

    public boolean retornaJogadorWin() {
        return jogadorWin;
    }

    public boolean retornaComputadorWin() {
        return computadorWin;
    }

    public void verificaPosicao(String posicaoJogador) {
        int novaPosicaoJogador = 0;
        boolean posicaoJogadorValidada = true;

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
                posicaoJogadorValidada = false;
                System.out.println(TerminalColors.RED + "Posição inválida." + TerminalColors.RESET);
                esperar(3000);
        }

        if (posicaoJogadorValidada) {
            if (posicao[novaPosicaoJogador] == 0) {
                posicao[novaPosicaoJogador] = 4;
                jogadorJogou = true;
            } else {
                System.out.println(
                    TerminalColors.RED +
                    "Posição já está oculpada, escolha outra posição." +
                    TerminalColors.RESET);
                esperar(3000);
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
        int[] posicoesPossiveis = {0, 2, 4, 10, 12, 14, 20, 22, 24};
        boolean procurandoPosicao = true;

        while (procurandoPosicao) {
            int posicaoSorteada = random.nextInt(9);
            int posicaoComputador = posicoesPossiveis[posicaoSorteada];

            if (posicao[posicaoComputador] == 0) {
                posicao[posicaoComputador] = 1;
                procurandoPosicao = false;
            }
        }
    }

    public void verificaVencedor() {
        /*
          Posicoes: 1, 2, 3,  4,  5,  6,  7,  8,  9
             Index: 0, 2, 4, 10, 12, 14, 20, 22, 24

          Combinações vencedoras:
            Linhas:
                - Primeira linha (células 1, 2 e 3) ou (0, 2, e 4)
                - Segunda linha (células 4, 5 e 6) ou (10, 12 e 14)
                - Terceira linha (células 7, 8 e 9) ou (20, 22 e 24)
            Colunas:
                - Primeira coluna (células 1, 4 e 7) ou (0, 10 e 20)
                - Segunda coluna (células 2, 5 e 8) ou (2, 12 e 22)
                - Terceira coluna (células 3, 6 e 9) ou (4, 14 e 24)
            Diagonais:
                - Diagonal principal (células 1, 5 e 9) ou (0, 12 e 24)
                - Diagonal secundária (células 3, 5 e 7) ou (4, 12 e 20)
            
            Número jogador: 4
            Número computador: 1
         */
        if (posicao[0] == 4 && posicao[2] == 4 && posicao[4] == 4) {
            jogadorWin = true;
        } else if (posicao[10] == 4 && posicao[12] == 4 && posicao[14] == 4) {
            jogadorWin = true;
        } else if (posicao[20] == 4 && posicao[22] == 4 && posicao[24] == 4) {
            jogadorWin = true;
        } else if (posicao[0] == 4 && posicao[10] == 4 && posicao[20] == 4) {
            jogadorWin = true;
        } else if (posicao[2] == 4 && posicao[12] == 4 && posicao[22] == 4) {
            jogadorWin = true;
        } else if (posicao[4] == 4 && posicao[14] == 4 && posicao[24] == 4) {
            jogadorWin = true;
        } else if (posicao[0] == 4 && posicao[12] == 4 && posicao[24] == 4) {
            jogadorWin = true;
        } else if (posicao[4] == 4 && posicao[12] == 4 && posicao[20] == 4) {
            jogadorWin = true;
        }

        if (posicao[0] == 1 && posicao[2] == 1 && posicao[4] == 1) {
            computadorWin = true;
        } else if (posicao[10] == 1 && posicao[12] == 1 && posicao[14] == 1) {
            computadorWin = true;
        } else if (posicao[20] == 1 && posicao[22] == 1 && posicao[24] == 1) {
            computadorWin = true;
        } else if (posicao[0] == 1 && posicao[10] == 1 && posicao[20] == 1) {
            computadorWin = true;
        } else if (posicao[2] == 1 && posicao[12] == 1 && posicao[22] == 1) {
            computadorWin = true;
        } else if (posicao[4] == 1 && posicao[14] == 1 && posicao[24] == 1) {
            computadorWin = true;
        } else if (posicao[0] == 1 && posicao[12] == 1 && posicao[24] == 1) {
            computadorWin = true;
        } else if (posicao[4] == 1 && posicao[12] == 1 && posicao[20] == 1) {
            computadorWin = true;
        }
    }

    public static void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }
}
