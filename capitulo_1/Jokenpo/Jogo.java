import java.util.Random;

public class Jogo {
    private int scoreJogador = 0;
    private int scoreComputador = 0;
    private boolean emAndamento = true;
    private final Random random = new Random();

    public boolean estaEmAndamento() {
        return emAndamento;
    }

    public void encerrar() {
        emAndamento = false;
    }

    public int getScoreJogador() {
        return scoreJogador;
    }

    public int getScoreComputador() {
        return scoreComputador;
    }

    public int jogadaComputador() {
        return random.nextInt(3) + 1;
    }

    public int compararJogada(int jogador, int computador) {
        if (jogador == computador) return 0;
        if ((jogador == 1 && computador == 3) ||
            (jogador == 2 && computador == 1) ||
            (jogador == 3 && computador == 2)) {
                scoreJogador++;
                return 1;
        } else {
            scoreComputador++;
            return -1;
        }
    }
}