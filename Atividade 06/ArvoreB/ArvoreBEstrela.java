
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArvoreBEstrela {

    private NoBEstrela raiz;
    private int maxChaves;

    public ArvoreBEstrela(int maxChaves) {
        this.maxChaves = maxChaves;
        this.raiz = new NoBEstrela(maxChaves, true);
    }

    public void inserir(int chave, String valor) {
        NoBEstrela noAlvo = encontrarNoAlvo(raiz, chave);

        if (noAlvo.buscarValor(chave) != null) {
            int indice = noAlvo.obterChaves().indexOf(chave);
            noAlvo.obterValores().set(indice, valor);
            return;
        }

        noAlvo.inserirChaveValor(chave, valor);

        if (noAlvo.estaCheio()) {
            tratarOverflow(noAlvo);
        }
    }

    public String buscar(int chave) {
        return buscarNoNo(raiz, chave);
    }

    public boolean remover(int chave) {
        NoBEstrela noAlvo = encontrarNoContendoChave(raiz, chave);
        if (noAlvo == null) {
            return false;
        }

        if (noAlvo.ehFolha()) {
            noAlvo.removerChaveValor(chave);
        } else {
            removerDeNoInterno(noAlvo, chave);
        }

        if (!noAlvo.temMinimoDeChaves() && noAlvo != raiz) {
            tratarUnderflow(noAlvo);
        } else if (noAlvo == raiz && noAlvo.obterChaves().isEmpty() && !noAlvo.ehFolha() && !noAlvo.obterFilhos().isEmpty()) {
            raiz = noAlvo.obterFilhos().get(0);
            raiz.definirPai(null);
        }
        return true;
    }

    private NoBEstrela encontrarNoAlvo(NoBEstrela no, int chave) {
        if (no.ehFolha()) {
            return no;
        }

        int i = 0;
        while (i < no.obterChaves().size() && chave > no.obterChaves().get(i)) {
            i++;
        }
        return encontrarNoAlvo(no.obterFilhos().get(i), chave);
    }

    private String buscarNoNo(NoBEstrela no, int chave) {
        String valor = no.buscarValor(chave);
        if (valor != null) {
            return valor;
        }

        if (no.ehFolha()) {
            return null;
        }

        int indiceFilho = no.encontrarIndiceFilho(chave);
        return buscarNoNo(no.obterFilhos().get(indiceFilho), chave);
    }

    private void tratarOverflow(NoBEstrela no) {
        if (no == raiz) {
            dividirRaiz();
            return;
        }

        if (tentarRedistribuir(no)) {
            return;
        }

        dividirNo(no);
    }

    private boolean tentarRedistribuir(NoBEstrela no) {
        NoBEstrela pai = no.obterPai();
        if (pai == null) {
            return false;
        }

        int indiceNo = pai.obterFilhos().indexOf(no);

        if (indiceNo > 0) {
            NoBEstrela irmaoEsquerdo = pai.obterFilhos().get(indiceNo - 1);
            if (irmaoEsquerdo.podeEmprestarChave()) {
                redistribuirComIrmaoEsquerdo(no, irmaoEsquerdo, indiceNo - 1);
                return true;
            }
        }

        if (indiceNo < pai.obterFilhos().size() - 1) {
            NoBEstrela irmaoDireito = pai.obterFilhos().get(indiceNo + 1);
            if (irmaoDireito.podeEmprestarChave()) {
                redistribuirComIrmaoDireito(no, irmaoDireito, indiceNo);
                return true;
            }
        }
        return false;
    }

    private void redistribuirComIrmaoEsquerdo(NoBEstrela no, NoBEstrela irmaoEsquerdo, int indiceChavePai) {
        NoBEstrela pai = no.obterPai();
        int chavePai = pai.obterChaves().remove(indiceChavePai);
        String valorPai = pai.obterValores().remove(indiceChavePai);
        no.obterChaves().add(0, chavePai);
        no.obterValores().add(0, valorPai);

        int ultimoIndice = irmaoEsquerdo.obterChaves().size() - 1;
        int chaveMovida = irmaoEsquerdo.obterChaves().remove(ultimoIndice);
        String valorMovido = irmaoEsquerdo.obterValores().remove(ultimoIndice);
        pai.obterChaves().add(indiceChavePai, chaveMovida);
        pai.obterValores().add(indiceChavePai, valorMovido);

        if (!no.ehFolha()) {
            NoBEstrela filhoMovido = irmaoEsquerdo.obterFilhos().remove(irmaoEsquerdo.obterFilhos().size() - 1);
            no.obterFilhos().add(0, filhoMovido);
            filhoMovido.definirPai(no);
        }
    }

    private void redistribuirComIrmaoDireito(NoBEstrela no, NoBEstrela irmaoDireito, int indiceChavePai) {
        NoBEstrela pai = no.obterPai();
        int chavePai = pai.obterChaves().remove(indiceChavePai);
        String valorPai = pai.obterValores().remove(indiceChavePai);
        no.obterChaves().add(chavePai);
        no.obterValores().add(valorPai);

        int chaveMovida = irmaoDireito.obterChaves().remove(0);
        String valorMovido = irmaoDireito.obterValores().remove(0);
        pai.obterChaves().add(indiceChavePai, chaveMovida);
        pai.obterValores().add(indiceChavePai, valorMovido);

        if (!no.ehFolha()) {
            NoBEstrela filhoMovido = irmaoDireito.obterFilhos().remove(0);
            no.obterFilhos().add(filhoMovido);
            filhoMovido.definirPai(no);
        }
    }

    private void dividirNo(NoBEstrela no) {
        NoBEstrela novoNo = new NoBEstrela(maxChaves, no.ehFolha());

        int indiceMeio = no.obterChaves().size() / 2;
        int chavePromovida = no.obterChaves().remove(indiceMeio);
        String valorPromovido = no.obterValores().remove(indiceMeio);

        for (int i = no.obterChaves().size(); i > indiceMeio; i--) {
            novoNo.obterChaves().add(0, no.obterChaves().remove(i - 1));
            novoNo.obterValores().add(0, no.obterValores().remove(i - 1));
        }

        if (!no.ehFolha()) {
            int indiceFilhoMeio = indiceMeio + 1;
            for (int i = no.obterFilhos().size() - 1; i >= indiceFilhoMeio; i--) {
                NoBEstrela filho = no.obterFilhos().remove(i);
                novoNo.obterFilhos().add(0, filho);
                filho.definirPai(novoNo);
            }
        }

        NoBEstrela pai = no.obterPai();
        if (pai == null) {
            NoBEstrela novaRaiz = new NoBEstrela(maxChaves, false);
            novaRaiz.inserirChaveValor(chavePromovida, valorPromovido);
            novaRaiz.obterFilhos().add(no);
            novaRaiz.obterFilhos().add(novoNo);
            no.definirPai(novaRaiz);
            novoNo.definirPai(novaRaiz);
            raiz = novaRaiz;
        } else {
            pai.inserirChaveValor(chavePromovida, valorPromovido);

            int indiceInsercao = pai.obterFilhos().indexOf(no) + 1;
            pai.obterFilhos().add(indiceInsercao, novoNo);
            novoNo.definirPai(pai);

            if (pai.estaCheio()) {
                tratarOverflow(pai);
            }
        }
    }

    private void dividirRaiz() {
        NoBEstrela novaRaiz = new NoBEstrela(maxChaves, false);
        int indiceMeio = raiz.obterChaves().size() / 2;
        int chavePromovida = raiz.obterChaves().remove(indiceMeio);
        String valorPromovido = raiz.obterValores().remove(indiceMeio);

        NoBEstrela metadeDireita = new NoBEstrela(maxChaves, raiz.ehFolha());

        for (int i = raiz.obterChaves().size(); i > indiceMeio; i--) {
            metadeDireita.obterChaves().add(0, raiz.obterChaves().remove(i - 1));
            metadeDireita.obterValores().add(0, raiz.obterValores().remove(i - 1));
        }

        if (!raiz.ehFolha()) {
            int indiceFilhoMeio = indiceMeio + 1;
            for (int i = raiz.obterFilhos().size() - 1; i >= indiceFilhoMeio; i--) {
                NoBEstrela filho = raiz.obterFilhos().remove(i);
                metadeDireita.obterFilhos().add(0, filho);
                filho.definirPai(metadeDireita);
            }
        }

        novaRaiz.inserirChaveValor(chavePromovida, valorPromovido);
        novaRaiz.obterFilhos().add(raiz);
        novaRaiz.obterFilhos().add(metadeDireita);
        raiz.definirPai(novaRaiz);
        metadeDireita.definirPai(novaRaiz);
        raiz = novaRaiz;
    }

    private void tratarUnderflow(NoBEstrela no) {
        NoBEstrela pai = no.obterPai();
        if (pai == null) {
            if (no.obterChaves().isEmpty() && !no.ehFolha() && !no.obterFilhos().isEmpty()) {
                raiz = no.obterFilhos().get(0);
                raiz.definirPai(null);
            }
            return;
        }

        int indiceNo = pai.obterFilhos().indexOf(no);

        if (indiceNo > 0) {
            NoBEstrela irmaoEsquerdo = pai.obterFilhos().get(indiceNo - 1);
            if (irmaoEsquerdo.podeEmprestarChave()) {
                emprestarDeIrmaoEsquerdo(no, irmaoEsquerdo, indiceNo - 1);
                return;
            }
        }

        if (indiceNo < pai.obterFilhos().size() - 1) {
            NoBEstrela irmaoDireito = pai.obterFilhos().get(indiceNo + 1);
            if (irmaoDireito.podeEmprestarChave()) {
                emprestarDeIrmaoDireito(no, irmaoDireito, indiceNo);
                return;
            }
        }

        if (indiceNo > 0) {
            mesclarComIrmaoEsquerdo(no, indiceNo - 1);
        } else {
            mesclarComIrmaoDireito(no, indiceNo);
        }
    }

    private void emprestarDeIrmaoEsquerdo(NoBEstrela no, NoBEstrela irmaoEsquerdo, int indiceChavePai) {
        NoBEstrela pai = no.obterPai();

        int chavePai = pai.obterChaves().remove(indiceChavePai);
        String valorPai = pai.obterValores().remove(indiceChavePai);
        no.obterChaves().add(0, chavePai);
        no.obterValores().add(0, valorPai);

        int ultimoIndice = irmaoEsquerdo.obterChaves().size() - 1;
        int chaveMovida = irmaoEsquerdo.obterChaves().remove(ultimoIndice);
        String valorMovido = irmaoEsquerdo.obterValores().remove(ultimoIndice);
        pai.obterChaves().add(indiceChavePai, chaveMovida);
        pai.obterValores().add(indiceChavePai, valorMovido);

        if (!no.ehFolha()) {
            NoBEstrela filhoMovido = irmaoEsquerdo.obterFilhos().remove(irmaoEsquerdo.obterFilhos().size() - 1);
            no.obterFilhos().add(0, filhoMovido);
            filhoMovido.definirPai(no);
        }
    }

    private void emprestarDeIrmaoDireito(NoBEstrela no, NoBEstrela irmaoDireito, int indiceChavePai) {
        NoBEstrela pai = no.obterPai();

        int chavePai = pai.obterChaves().remove(indiceChavePai);
        String valorPai = pai.obterValores().remove(indiceChavePai);
        no.obterChaves().add(chavePai);
        no.obterValores().add(valorPai);

        int chaveMovida = irmaoDireito.obterChaves().remove(0);
        String valorMovido = irmaoDireito.obterValores().remove(0);
        pai.obterChaves().add(indiceChavePai, chaveMovida);
        pai.obterValores().add(indiceChavePai, valorMovido);

        if (!no.ehFolha()) {
            NoBEstrela filhoMovido = irmaoDireito.obterFilhos().remove(0);
            no.obterFilhos().add(filhoMovido);
            filhoMovido.definirPai(no);
        }
    }

    private void mesclarComIrmaoEsquerdo(NoBEstrela no, int indiceChavePai) {
        NoBEstrela pai = no.obterPai();
        NoBEstrela irmaoEsquerdo = pai.obterFilhos().get(indiceChavePai);

        int chavePai = pai.obterChaves().remove(indiceChavePai);
        String valorPai = pai.obterValores().remove(indiceChavePai);
        irmaoEsquerdo.obterChaves().add(chavePai);
        irmaoEsquerdo.obterValores().add(valorPai);

        irmaoEsquerdo.obterChaves().addAll(no.obterChaves());
        irmaoEsquerdo.obterValores().addAll(no.obterValores());

        if (!no.ehFolha()) {
            for (NoBEstrela filho : no.obterFilhos()) {
                irmaoEsquerdo.obterFilhos().add(filho);
                filho.definirPai(irmaoEsquerdo);
            }
        }

        pai.obterFilhos().remove(no);

        if (!pai.temMinimoDeChaves() && pai != raiz) {
            tratarUnderflow(pai);
        } else if (pai == raiz && pai.obterChaves().isEmpty()) {
            raiz = irmaoEsquerdo;
            irmaoEsquerdo.definirPai(null);
        }
    }

    private void mesclarComIrmaoDireito(NoBEstrela no, int indiceChavePai) {
        NoBEstrela pai = no.obterPai();
        NoBEstrela irmaoDireito = pai.obterFilhos().get(indiceChavePai + 1);

        int chavePai = pai.obterChaves().remove(indiceChavePai);
        String valorPai = pai.obterValores().remove(indiceChavePai);
        no.obterChaves().add(chavePai);
        no.obterValores().add(valorPai);

        no.obterChaves().addAll(irmaoDireito.obterChaves());
        no.obterValores().addAll(irmaoDireito.obterValores());

        if (!no.ehFolha()) {
            for (NoBEstrela filho : irmaoDireito.obterFilhos()) {
                no.obterFilhos().add(filho);
                filho.definirPai(no);
            }
        }

        pai.obterFilhos().remove(irmaoDireito);

        if (!pai.temMinimoDeChaves() && pai != raiz) {
            tratarUnderflow(pai);
        } else if (pai == raiz && pai.obterChaves().isEmpty()) {
            raiz = no;
            no.definirPai(null);
        }
    }

    private NoBEstrela encontrarNoContendoChave(NoBEstrela no, int chave) {
        if (no == null) {
            return null;
        }

        int indice = Collections.binarySearch(no.obterChaves(), chave);
        if (indice >= 0) {
            return no;
        }

        if (no.ehFolha()) {
            return null;
        }

        int indiceFilho = no.encontrarIndiceFilho(chave);
        return encontrarNoContendoChave(no.obterFilhos().get(indiceFilho), chave);
    }

    private void removerDeNoInterno(NoBEstrela no, int chave) {
        int indiceChave = no.obterChaves().indexOf(chave);

        NoBEstrela sucessor = no.obterFilhos().get(indiceChave + 1);
        while (!sucessor.ehFolha()) {
            sucessor = sucessor.obterFilhos().get(0);
        }

        int chaveSucessora = sucessor.obterChaves().get(0);
        String valorSucessor = sucessor.obterValores().get(0);

        no.obterChaves().set(indiceChave, chaveSucessora);
        no.obterValores().set(indiceChave, valorSucessor);

        sucessor.removerChaveValor(chaveSucessora);

        if (!sucessor.temMinimoDeChaves() && sucessor != raiz) {
            tratarUnderflow(sucessor);
        }
    }

    public List<String> obterTodosOsProdutosEmOrdem() {
        List<String> produtos = new ArrayList<>();
        travessiaEmOrdem(raiz, produtos);
        return produtos;
    }

    private void travessiaEmOrdem(NoBEstrela no, List<String> produtos) {
        if (no == null) {
            return;
        }

        if (no.ehFolha()) {
            for (int i = 0; i < no.obterChaves().size(); i++) {
                produtos.add("ID: " + no.obterChaves().get(i) + ", Info: " + no.obterValores().get(i));
            }
        } else {
            for (int i = 0; i < no.obterChaves().size(); i++) {
                travessiaEmOrdem(no.obterFilhos().get(i), produtos);
                produtos.add("ID: " + no.obterChaves().get(i) + ", Info: " + no.obterValores().get(i));
            }
            travessiaEmOrdem(no.obterFilhos().get(no.obterFilhos().size() - 1), produtos);
        }
    }
}
