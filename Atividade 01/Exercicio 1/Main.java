import javax.swing.JOptionPane;

public class Main {
    private static FilaImpressao filaImpressao = new FilaImpressao();

    public static void main(String[] args) {
        while (true) {
            String opcao = exibirMenu();

            if (opcao == null || opcao.equals("5")) {
                break;
            }

            switch (opcao) {
                case "1":
                    adicionarTrabalho();
                    break;
                case "2":
                    imprimirProximoTrabalho();
                    break;
                case "3":
                    exibirFila();
                    break;
                case "4":
                    verificarFilaVazia();
                    break;
                default:
                    System.out.println("Operacao Invalida.");
            }
        }
    }

    private static String exibirMenu() {
        return JOptionPane.showInputDialog(
            "Digite A Operacao:\n" +
            "1 - Adicionar Trabalho\n" +
            "2 - Imprimir Proximo Trabalho\n" +
            "3 - Exibir Fila De Impressao\n" +
            "4 - Verificar Se A Fila Esta Vazia\n" +
            "5 - Sair"
        );
    }

    private static void adicionarTrabalho() {
        int idAluno = Integer.parseInt(JOptionPane.showInputDialog("Digite O ID Do Aluno:"));
        String nomeArquivo = JOptionPane.showInputDialog("Digite O Nome Do Arquivo:");
        int numeroPaginas = Integer.parseInt(JOptionPane.showInputDialog("Digite O Numero De Paginas:"));
        TrabalhoImpressao trabalho = new TrabalhoImpressao(idAluno, nomeArquivo, numeroPaginas);
        filaImpressao.adicionar(trabalho);
        System.out.println("Trabalho Adicionado A Fila De Impressao.");
    }

    private static void imprimirProximoTrabalho() {
        if (filaImpressao.estaVazia()) {
            System.out.println("A Fila De Impressao Esta Vazia.");
        } else {
            TrabalhoImpressao trabalho = filaImpressao.imprimirProximo();
            System.out.println("Imprimindo Trabalho: " + trabalho);
        }
    }

    private static void exibirFila() {
        if (filaImpressao.estaVazia()) {
            System.out.println("A Fila De Impressao Esta Vazia.");
        } else {
            System.out.println("Fila De Impressao:");
            filaImpressao.exibir();
        }
    }

    private static void verificarFilaVazia() {
        boolean vazia = filaImpressao.estaVazia();
        System.out.println("A Fila Esta Vazia: " + (vazia ? "Verdadeiro" : "Falso"));
    }
}