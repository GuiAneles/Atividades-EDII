public class No {
    private int chave;
    private String valor;
    private No proximo;

    public No(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
        this.proximo = null;
    }

    public int getChave() {
        return chave;
    }

    public String getValor() {
        return valor;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
