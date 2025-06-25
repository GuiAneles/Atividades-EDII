
public class ExemploTabelaHash {

    public static void main(String[] args) {
        TabelaHash minhaTabela = new TabelaHash();

        System.out.println("=== DEMONSTRAÇÃO DE TABELA HASH ===\n");

        System.out.println("1. INSERINDO ELEMENTOS:");
        minhaTabela.inserir(10, "Dez");
        minhaTabela.inserir(22, "Vinte e Dois");
        minhaTabela.inserir(31, "Trinta e Um");
        minhaTabela.inserir(4, "Quatro");
        minhaTabela.inserir(15, "Quinze");
        minhaTabela.inserir(28, "Vinte e Oito");
        minhaTabela.inserir(17, "Dezessete");
        minhaTabela.inserir(88, "Oitenta e Oito");
        minhaTabela.inserir(59, "Cinquenta e Nove");
        System.out.println();

        minhaTabela.exibirTabela();

        System.out.println("2. REALIZANDO BUSCAS:");
        System.out.println("Buscar chave 22: " + minhaTabela.buscar(22));
        System.out.println("Buscar chave 31: " + minhaTabela.buscar(31));
        System.out.println("Buscar chave 99: " + minhaTabela.buscar(99));
        System.out.println("Buscar chave 88: " + minhaTabela.buscar(88));
        System.out.println();

        System.out.println("3. ATUALIZANDO ELEMENTO:");
        minhaTabela.inserir(22, "Vinte e Dois Atualizado");
        System.out.println("Buscar chave 22 após atualização: " + minhaTabela.buscar(22));
        System.out.println();

        System.out.println("4. REMOVENDO ELEMENTOS:");
        minhaTabela.remover(31);
        minhaTabela.remover(99);
        minhaTabela.remover(88);
        System.out.println();

        System.out.println("5. ESTADO FINAL DA TABELA:");
        minhaTabela.exibirTabela();

        System.out.println("Tabela está vazia? " + minhaTabela.estaVazia());
        System.out.println("Número total de elementos: " + minhaTabela.getNumElementos());
    }
}
