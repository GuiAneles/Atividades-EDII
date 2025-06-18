
import java.util.ArrayList;
import java.util.List;

public class No {

    protected List<Integer> chaves;
    protected boolean ehFolha;
    protected No pai;
    protected int maxChaves;

    public No(int maxChaves, boolean ehFolha) {
        this.chaves = new ArrayList<>();
        this.ehFolha = ehFolha;
        this.maxChaves = maxChaves;
        this.pai = null;
    }

    public boolean estaCheio() {
        return chaves.size() >= maxChaves;
    }

    public boolean temMinimoDeChaves() {
        return chaves.size() >= (maxChaves + 1) / 2;
    }

    public List<Integer> obterChaves() {
        return chaves;
    }

    public boolean ehFolha() {
        return ehFolha;
    }

    public No obterPai() {
        return pai;
    }

    public void definirPai(No pai) {
        this.pai = pai;
    }
}
