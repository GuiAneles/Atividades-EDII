
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        ArvoreB biblioteca = new ArvoreB();

        while (true) {
            String menu = """
                    === Biblioteca com Árvore B ===
                    1. Inserir livro
                    2. Buscar livro por ISBN
                    3. Listar livros por título
                    4. Exibir estrutura da árvore
                    5. Sair
                    Escolha uma opção:
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break;
            }

            int opcao;
            try {
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida!");
                continue;
            }

            switch (opcao) {
                case 1 -> {
                    String titulo = JOptionPane.showInputDialog("Digite o título:");
                    String autor = JOptionPane.showInputDialog("Digite o autor:");
                    String isbnStr = JOptionPane.showInputDialog("Digite o ISBN:");

                    try {
                        int isbn = Integer.parseInt(isbnStr);
                        Livro livro = new Livro(titulo, autor, isbn);
                        biblioteca.inserir(livro);
                        JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ISBN inválido.");
                    }
                }
                case 2 -> {
                    String isbnStr = JOptionPane.showInputDialog("Digite o ISBN:");
                    try {
                        int isbn = Integer.parseInt(isbnStr);
                        if (!biblioteca.buscar(isbn)) {
                            JOptionPane.showMessageDialog(null, "Livro não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ISBN inválido.");
                    }
                }
                case 3 -> {
                    String livros = biblioteca.getLivrosEmOrdemPorTitulo();
                    JOptionPane.showMessageDialog(null, livros.isEmpty() ? "Nenhum livro cadastrado." : livros);
                }
                case 4 -> {
                    String estrutura = biblioteca.getEstrutura();
                    JOptionPane.showMessageDialog(null, estrutura);
                }
                case 5 -> {
                    JOptionPane.showMessageDialog(null, "Encerrando...");
                    return;
                }
                default ->
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}
