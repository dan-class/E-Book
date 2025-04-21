public class TreinandoCores {
    public static void main(String[] args) {
        System.out.println("Textos com cores diferentes:");

        TerminalColors.println("Este texto é vermelho", TerminalColors.RED);
        TerminalColors.println("Este texto é verde", TerminalColors.GREEN);
        TerminalColors.println("Este texto é amarelo", TerminalColors.YELLOW);
        TerminalColors.println("Este texto é azul", TerminalColors.BLUE);
        TerminalColors.println("Este texto é magenta", TerminalColors.MAGENTA);
        TerminalColors.println("Este texto é ciano", TerminalColors.CYAN);

        System.out.println("\nTextos com estilos:");

        System.out.println(TerminalColors.BOLD + "Este texto é negrito" + TerminalColors.RESET);
        System.out.println(TerminalColors.UNDERLINE + "Este texto é sublinhado" + TerminalColors.RESET);
        System.out.println(TerminalColors.INVERSE + "Este texto tem cores invertidas" + TerminalColors.RESET);

        System.out.println("\nTextos com fundo colorido:");

        System.out.println(TerminalColors.BG_RED + "Fundo vermelho" + TerminalColors.RESET);
        System.out.println(TerminalColors.BG_GREEN + "Fundo verde" + TerminalColors.RESET);
        System.out.println(TerminalColors.BG_BLUE + "Fundo azul" + TerminalColors.RESET);

        System.out.println("\nCombinando cores e estilos:");

        System.out.println(TerminalColors.RED + TerminalColors.BG_YELLOW + "Texto vermelho com fundo amarelo" + TerminalColors.RESET);
        System.out.println(TerminalColors.BOLD + TerminalColors.GREEN + "Texto verde em negrito" + TerminalColors.RESET);
        System.out.println(TerminalColors.UNDERLINE + TerminalColors.CYAN + "Texto ciano sublinhado" + TerminalColors.RESET);
    }
}
