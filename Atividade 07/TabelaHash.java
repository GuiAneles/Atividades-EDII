
public class TabelaHash {

    private No[] tabela;
    private int tamanho;
    private int numElementos;

    public TabelaHash() {
        this.tamanho = 10;
        this.tabela = new No[tamanho];
        this.numElementos = 0;
    }

    private int funcaoHash(int chave) {
        return chave % tamanho;
    }

    public void inserir(int chave, String valor) {
        int indice = funcaoHash(chave);

        No atual = tabela[indice];

        while (atual != null) {
            if (atual.getChave() == chave) {
                atual.setValor(valor);
                System.out.println("Atualizado: (" + chave + ", " + valor + ") no índice " + indice);
                return;
            }
            atual = atual.getProximo();
        }

        No novoNo = new No(chave, valor);
        novoNo.setProximo(tabela[indice]);
        tabela[indice] = novoNo;
        numElementos++;

        if (novoNo.getProximo() == null) {
            System.out.println("Inserido: (" + chave + ", " + valor + ") no índice " + indice);
        } else {
            System.out.println("Inserido com colisão: (" + chave + ", " + valor + ") no índice " + indice);
        }
    }

    public String buscar(int chave) {
        int indice = funcaoHash(chave);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.getChave() == chave) {
                return atual.getValor();
            }
            atual = atual.getProximo();
        }

        return "CHAVE_NAO_ENCONTRADA";
    }

    public boolean remover(int chave) {
        int indice = funcaoHash(chave);
        No atual = tabela[indice];
        No anterior = null;

        while (atual != null) {
            if (atual.getChave() == chave) {
                if (anterior == null) {
                    tabela[indice] = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                numElementos--;
                System.out.println("Removido: chave " + chave + " do índice " + indice);
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }

        System.out.println("Chave " + chave + " não encontrada para remoção");
        return false;
    }

    public void exibirTabela() {
        System.out.println("=== ESTADO DA TABELA HASH ===");
        System.out.println("Número de elementos: " + numElementos);
        System.out.println("Tamanho da tabela: " + tamanho);
        System.out.println("Fator de carga: " + (float) numElementos / tamanho);
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            System.out.print("Índice [" + i + "]: ");
            No atual = tabela[i];
            if (atual == null) {
                System.out.println("vazio");
            } else {
                while (atual != null) {
                    System.out.print("(" + atual.getChave() + ", " + atual.getValor() + ")");
                    atual = atual.getProximo();
                    if (atual != null) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }

        System.out.println("=============================");
        System.out.println();
    }

    public boolean estaVazia() {
        return numElementos == 0;
    }

    public int getNumElementos() {
        return numElementos;
    }
}
