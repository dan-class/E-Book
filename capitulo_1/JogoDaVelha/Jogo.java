public class Jogo {
    private int scoreJogador = 0;
    private int scoreComputador = 0;
    private boolean emAndamento = false;
    private int[] posicao = {
        0, 2, 0, 2, 0, 
        2, 2, 2, 2, 2,
        0, 2, 0, 2, 0, 
        2, 2, 2, 2, 2,
        0, 2, 0, 2, 0
    };
    private char[] mapaJogo = {
        ' ', '|', ' ', '|', ' ',
        '-', '-', '-', '-', '-',
        ' ', '|', ' ', '|', ' ',
        '-', '-', '-', '-', '-',
        ' ', '|', ' ', '|', ' '
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

    public void verificaPosicao(char posicaoJogador) {
        for (int i = 0; i < posicao.length; i++) {

            int novaPosicaoJogador = 0;

            switch (posicaoJogador) {
                case '1':
                    novaPosicaoJogador = 0;
                    break;
                case '2':
                    novaPosicaoJogador = 2;
                    break;
                case '3':
                    novaPosicaoJogador = 4;
                    break;
                case '4':
                    novaPosicaoJogador = 10;
                    break;
                case '5':
                    novaPosicaoJogador = 12;
                    break;
                case '6':
                    novaPosicaoJogador = 14;
                    break;
                case '7':
                    novaPosicaoJogador = 20;
                    break;
                case '8':
                    novaPosicaoJogador = 22;
                    break;
                case '9':
                    novaPosicaoJogador = 24;
                    break;
                default:
                    break;
            }

            if (posicao[novaPosicaoJogador] == 0) {
                posicao[novaPosicaoJogador] = 1;
            } else {
                System.out.println(
                    TerminalColors.RED +
                    "Posição já está oculpada, escolha outra posição." +
                    TerminalColors.RESET);
            }
        }
    }

    public static void mostraMapaJogo() {
        // TODO: terminar a função para mostrar o mapa
    }

}
