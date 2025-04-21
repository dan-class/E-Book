import java.util.Scanner;

/**
 * Programa para exibir todas as combinações de cores disponíveis no terminal
 */
public class Cores {
    
    // Constantes para mensagens
    private static final String HELP_MESSAGE = 
            "ColorPalette\n\n" +
            "O programa exibe as cores em dois formatos:\n" +
            "\t1 - Cores com fundo colorido\n" +
            "\t2 - Cores com fundo padrão do terminal\n" +
            "\n\tUtilização: java ColorPalette [opção]\n" +
            "\t\t-h, --help  - Mostra uma ajuda de como utilizar o programa.\n" +
            "\t\t-cf - Mostra os códigos de cores com fundo.\n" +
            "\t\t-c  - Mostra os códigos de cores sem fundo.\n\n" +
            "O programa também pode ser utilizado sem parâmetros, dessa forma\n" +
            "ele irá mostrar um menu de opções.\n";
    
    private static final String ERROR_MESSAGE = 
            "\n\033[1;31mOpção inválida!\nUtilize: java ColorPalette -h para ajuda.\033[m";
    
    public static void main(String[] args) {
        // Verifica se há argumentos de linha de comando
        if (args.length == 0) {
            showMenu();
        } else {
            switch (args[0]) {
                case "-h":
                case "--help":
                    System.out.println(HELP_MESSAGE);
                    break;
                case "-c":
                    showColorsWithoutBackground();
                    break;
                case "-cf":
                    showColorsWithBackground();
                    break;
                default:
                    System.out.println(ERROR_MESSAGE);
                    System.exit(1);
            }
        }
    }
    
    /**
     * Exibe um menu interativo para o usuário
     */
    private static void showMenu() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print(
            "\nEscolha uma opção:\n" +
            "\t[ 1 ] - Cores com fundo\n" +
            "\t[ 2 ] - Cores sem fundo\n" +
            "\t[ 3 ] - Ajuda\n" +
            "\tOpção: "
        );
        
        String option = scanner.nextLine();
        
        switch (option) {
            case "1":
                showColorsWithBackground();
                break;
            case "2":
                showColorsWithoutBackground();
                break;
            case "3":
                clearScreen();
                System.out.println(HELP_MESSAGE);
                break;
            default:
                System.out.println(ERROR_MESSAGE);
                System.exit(1);
        }
        
        scanner.close();
    }
    
    /**
     * Mostra todas as combinações de cores com fundos coloridos
     */
    private static void showColorsWithBackground() {
        clearScreen();
        System.out.println();
        
        // Percorre todas as cores de texto (0-7)
        for (int textColor = 0; textColor <= 7; textColor++) {
            // Percorre todos os estilos (normal, negrito, sublinhado, inverso)
            for (String style : new String[]{"", "1;", "4;", "7;"}) {
                // Percorre todas as cores de fundo (0-7)
                for (int bgColor = 0; bgColor <= 7; bgColor++) {
                    String code = style + "3" + textColor + ";4" + bgColor;
                    System.out.print("\033[" + code + "m");
                    System.out.print(" " + (style.isEmpty() ? "  " : style) + "3" + textColor + ";4" + bgColor + "m ");
                    System.out.print("\033[m");
                }
                System.out.println();
            }
        }
    }
    
    /**
     * Mostra todas as combinações de cores sem fundos coloridos
     */
    private static void showColorsWithoutBackground() {
        clearScreen();
        System.out.println();
        
        // Percorre todas as cores de texto (0-7)
        for (int textColor = 0; textColor <= 7; textColor++) {
            // Percorre todos os estilos (normal, negrito, sublinhado, inverso)
            for (String style : new String[]{"", "1;", "4;", "7;"}) {
                String code = style + "3" + textColor;
                System.out.print("\033[" + code + "m");
                System.out.print(" " + (style.isEmpty() ? "  " : style) + "3" + textColor + "m ");
                System.out.print("\033[m");
            }
            System.out.println();
        }
    }
    
    /**
     * Limpa a tela do terminal
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}