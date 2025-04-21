public class Jogo {
    private int scoreJogador = 0;
    private int scoreComputador = 0;
    private boolean emAndamento = false;
    private int[] posicao = {0, 0, 0, 0, 0, 0, 0, 0, 0};

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

}
