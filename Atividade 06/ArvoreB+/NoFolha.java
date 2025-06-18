
import java.util.ArrayList;
import java.util.List;

public class NoFolha extends No {

    private List<String> valores;
    private NoFolha proximo;
    private NoFolha anterior;

    public NoFolha(int maxChaves) {
        super(maxChaves, true);
        this.valores = new ArrayList<>();
        this.proximo = null;
        this.anterior = null;
    }

    public void inserir(int chave, String valor) {
        int posicaoInsercao = 0;
        while (posicaoInsercao < chaves.size() && chaves.get(posicaoInsercao) < chave) {
            posicaoInsercao++;
        }
        chaves.add(posicaoInsercao, chave);
        valores.add(posicaoInsercao, valor);
    }

    public boolean remover(int chave) {
        int indice = chaves.indexOf(chave);
        if (indice != -1) {
            chaves.remove(indice);
            valores.remove(indice);
            return true;
        }
        return false;
    }

    public String buscar(int chave) {
        int indice = chaves.indexOf(chave);
        return indice != -1 ? valores.get(indice) : null;
    }

    public NoFolha dividir() {
        int pontoMedio = chaves.size() / 2;
        NoFolha novaFolha = new NoFolha(maxChaves);

        for (int i = pontoMedio; i < chaves.size(); i++) {
            novaFolha.chaves.add(chaves.get(i));
            novaFolha.valores.add(valores.get(i));
        }

        chaves.subList(pontoMedio, chaves.size()).clear();
        valores.subList(pontoMedio, valores.size()).clear();

        novaFolha.proximo = this.proximo;
        novaFolha.anterior = this;
        if (this.proximo != null) {
            this.proximo.anterior = novaFolha;
        }
        this.proximo = novaFolha;

        return novaFolha;
    }

    public List<String> obterValores() {
        return valores;
    }

    public NoFolha obterProximo() {
        return proximo;
    }

    public NoFolha obterAnterior() {
        return anterior;
    }

    public void definirProximo(NoFolha proximo) {
        this.proximo = proximo;
    }

    public void definirAnterior(NoFolha anterior) {
        this.anterior = anterior;
    }
}
