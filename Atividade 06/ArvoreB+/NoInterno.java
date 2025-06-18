
import java.util.ArrayList;
import java.util.List;

public class NoInterno extends No {

    private List<No> filhos;

    public NoInterno(int maxChaves) {
        super(maxChaves, false);
        this.filhos = new ArrayList<>();
    }

    public void adicionarFilho(No filho) {
        filhos.add(filho);
        filho.definirPai(this);
    }

    public void inserirFilho(int indice, No filho) {
        filhos.add(indice, filho);
        filho.definirPai(this);
    }

    public void removerFilho(No filho) {
        filhos.remove(filho);
    }

    public No encontrarFilho(int chave) {
        int i = 0;
        while (i < chaves.size() && chave >= chaves.get(i)) {
            i++;
        }
        return filhos.get(i);
    }

    public List<No> obterFilhos() {
        return filhos;
    }

    public No obterFilho(int indice) {
        return filhos.get(indice);
    }
}
