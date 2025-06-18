
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoBEstrela {

    protected List<Integer> chaves;
    protected List<String> valores;
    protected List<NoBEstrela> filhos;
    protected boolean ehFolha;
    protected NoBEstrela pai;
    protected int maxChaves;
    protected int minChaves;

    public NoBEstrela(int maxChaves, boolean ehFolha) {
        this.chaves = new ArrayList<>();
        this.valores = new ArrayList<>();
        this.filhos = ehFolha ? null : new ArrayList<>();
        this.ehFolha = ehFolha;
        this.maxChaves = maxChaves;
        this.minChaves = (int) Math.ceil((maxChaves * 2.0) / 3.0);
        this.pai = null;
    }

    public boolean estaCheio() {
        return chaves.size() >= maxChaves;
    }

    public boolean temMinimoDeChaves() {
        return chaves.size() >= minChaves;
    }

    public boolean podeEmprestarChave() {
        return chaves.size() > minChaves;
    }

    public void inserirChaveValor(int chave, String valor) {
        int posicaoInsercao = 0;
        while (posicaoInsercao < chaves.size() && chaves.get(posicaoInsercao) < chave) {
            posicaoInsercao++;
        }
        chaves.add(posicaoInsercao, chave);
        valores.add(posicaoInsercao, valor);
    }

    public String buscarValor(int chave) {
        int indice = Collections.binarySearch(chaves, chave);
        if (indice >= 0) {
            return valores.get(indice);
        }
        return null;
    }

    public int encontrarIndiceFilho(int chave) {
        int i = 0;
        while (i < chaves.size() && chave > chaves.get(i)) {
            i++;
        }
        return i;
    }

    public void removerChaveValor(int chave) {
        int indice = chaves.indexOf(chave);
        if (indice != -1) {
            chaves.remove(indice);
            valores.remove(indice);
        }
    }

    public List<Integer> obterChaves() {
        return chaves;
    }

    public List<String> obterValores() {
        return valores;
    }

    public List<NoBEstrela> obterFilhos() {
        return filhos;
    }

    public boolean ehFolha() {
        return ehFolha;
    }

    public NoBEstrela obterPai() {
        return pai;
    }

    public void definirPai(NoBEstrela pai) {
        this.pai = pai;
    }

    public int obterMaxChaves() {
        return maxChaves;
    }

    public int obterMinChaves() {
        return minChaves;
    }
}
