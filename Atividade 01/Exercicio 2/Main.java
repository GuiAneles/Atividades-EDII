import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EditorTexto editor = new EditorTexto();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite um comando (INSERIR, REMOVER, DESFAZER, REFAZER, IMPRIMIR, SAIR): ");
            String comando = scanner.nextLine().trim();

            if (comando.equalsIgnoreCase("SAIR")) {
                break;
            }

            String[] partes = comando.split(" ", 2);
            String acao = partes[0].toUpperCase();

            switch (acao) {
                case "INSERIR":
                    if (partes.length < 2) {
                        System.out.println("Uso: INSERIR <texto>");
                        break;
                    }
                    editor.inserir(partes[1]);
                    break;

                case "REMOVER":
                    if (partes.length < 2) {
                        System.out.println("Uso: REMOVER <n>");
                        break;
                    }
                    try {
                        int n = Integer.parseInt(partes[1]);
                        editor.remover(n);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido.");
                    }
                    break;

                case "DESFAZER":
                    editor.desfazer();
                    break;

                case "REFAZER":
                    editor.refazer();
                    break;

                case "IMPRIMIR":
                    editor.imprimir();
                    break;

                default:
                    System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}