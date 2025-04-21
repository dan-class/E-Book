/**
 * Utilirário para colorir textos no terminal usando códigos ANSI
 */
public class TerminalColors {
    // Constantes para reset e estilos
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String INVERSE = "\033[7m";

    // Constantes para cores de texto
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    // Constantes para cores de fundo
    public static final String BG_BLACK = "\033[40m";
    public static final String BG_RED = "\033[41m";
    public static final String BG_GREEN = "\033[42m";
    public static final String BG_YELLOW = "\033[43m";
    public static final String BG_BLUE = "\033[44m";
    public static final String BG_MAGENTA = "\033[45m";
    public static final String BG_CYAN = "\033[46m";
    public static final String BG_WHITE = "\033[47m";

    /**
     * Retorna o texto colorido
     * @param text O texto a ser colorido
     * @param color O código de cor ANSI
     * @return O texto com a cor aplicada e reset ao final
     */
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    /**
     * Imprime texto colorido no console
     * @param text O texto a ser impresso
     * @param color O código de cor ANSI
     */
    public static void print(String text, String color) {
        System.out.print(colorize(text, color));
    }

    /**
     * Imprime texto colorido no console com quebra de linha
     * @param text O texto a ser impresso
     * @param color O código de cor ANSI
     */
    public static void println(String text, String color) {
        System.out.println(colorize(text, color));
    }
}
