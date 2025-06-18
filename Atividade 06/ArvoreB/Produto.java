
public class Produto {

    private int id;
    private String nome;
    private String categoria;

    public Produto(int id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    public int obterId() {
        return id;
    }

    public String obterNome() {
        return nome;
    }

    public String obterCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return nome + " - " + categoria;
    }
}
