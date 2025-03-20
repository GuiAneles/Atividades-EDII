class FilaImpressao {
    private static class No {
        TrabalhoImpressao trabalho;
        No proximo;

        public No(TrabalhoImpressao trabalho) {
            this.trabalho = trabalho;
            this.proximo = null;
        }
    }

    private No inicio;
    private No fim;
    private int tamanho;

    public FilaImpressao() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void adicionar(TrabalhoImpressao trabalho) {
        No novoNo = new No(trabalho);
        if (fim == null) {
            inicio = fim = novoNo;
        } else {
            fim.proximo = novoNo;
            fim = novoNo;
        }
        tamanho++;
    }

    public TrabalhoImpressao imprimirProximo() {
        if (inicio == null) return null;
        TrabalhoImpressao trabalho = inicio.trabalho;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return trabalho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void exibir() {
        No atual = inicio;
        while (atual != null) {
            System.out.println(atual.trabalho);
            atual = atual.proximo;
        }
    }
}