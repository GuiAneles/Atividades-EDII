
import java.util.ArrayList;
import java.util.List;

public class ArvoreBMais {

    private No raiz;
    private int maxChaves;
    private NoFolha primeiraFolha;

    public ArvoreBMais(int maxChaves) {
        this.maxChaves = maxChaves;
        this.raiz = new NoFolha(maxChaves);
        this.primeiraFolha = (NoFolha) raiz;
    }

    public void inserir(int chave, String valor) {
        NoFolha folha = encontrarNoFolha(chave);

        int indice = folha.obterChaves().indexOf(chave);
        if (indice != -1) {
            folha.obterValores().set(indice, valor);
            return;
        }

        folha.inserir(chave, valor);

        if (folha.estaCheio()) {
            dividirNoFolha(folha);
        }
    }

    public String buscar(int chave) {
        NoFolha folha = encontrarNoFolha(chave);
        return folha.buscar(chave);
    }

    public boolean remover(int chave) {
        NoFolha folha = encontrarNoFolha(chave);
        boolean removido = folha.remover(chave);
        return removido;
    }

    private NoFolha encontrarNoFolha(int chave) {
        No atual = raiz;
        while (!atual.ehFolha()) {
            NoInterno interno = (NoInterno) atual;
            atual = interno.encontrarFilho(chave);
        }
        return (NoFolha) atual;
    }

    private void dividirNoFolha(NoFolha folha) {
        NoFolha novaFolha = folha.dividir();
        int chavePromovida = novaFolha.obterChaves().get(0);

        if (folha == raiz) {
            NoInterno novaRaiz = new NoInterno(maxChaves);
            novaRaiz.obterChaves().add(chavePromovida);
            novaRaiz.adicionarFilho(folha);
            novaRaiz.adicionarFilho(novaFolha);
            raiz = novaRaiz;
        } else {
            inserirNoPai(folha, chavePromovida, novaFolha);
        }
    }

    private void inserirNoPai(No filhoEsquerdo, int chave, No filhoDireito) {
        NoInterno pai;
        if (filhoEsquerdo.obterPai() == null) {
            pai = new NoInterno(maxChaves);
            pai.adicionarFilho(filhoEsquerdo);
            raiz = pai;
        } else {
            pai = (NoInterno) filhoEsquerdo.obterPai();
        }

        int posicaoInsercao = 0;
        while (posicaoInsercao < pai.obterChaves().size() && pai.obterChaves().get(posicaoInsercao) < chave) {
            posicaoInsercao++;
        }
        pai.obterChaves().add(posicaoInsercao, chave);
        pai.inserirFilho(posicaoInsercao + 1, filhoDireito);

        if (pai.estaCheio()) {
            dividirNoInterno(pai);
        }
    }

    private void dividirNoInterno(NoInterno no) {
        int pontoMedio = no.obterChaves().size() / 2;
        int chavePromovida = no.obterChaves().get(pontoMedio);

        NoInterno novoInterno = new NoInterno(maxChaves);

        for (int i = pontoMedio + 1; i < no.obterChaves().size(); i++) {
            novoInterno.obterChaves().add(no.obterChaves().get(i));
        }
        for (int i = pontoMedio + 1; i < no.obterFilhos().size(); i++) {
            novoInterno.adicionarFilho(no.obterFilhos().get(i));
        }

        no.obterChaves().subList(pontoMedio, no.obterChaves().size()).clear();
        no.obterFilhos().subList(pontoMedio + 1, no.obterFilhos().size()).clear();

        if (no == raiz) {
            NoInterno novaRaiz = new NoInterno(maxChaves);
            novaRaiz.obterChaves().add(chavePromovida);
            novaRaiz.adicionarFilho(no);
            novaRaiz.adicionarFilho(novoInterno);
            raiz = novaRaiz;
        } else {
            inserirNoPai(no, chavePromovida, novoInterno);
        }
    }

    public List<String> buscarIntervalo(int chaveInicio, int chaveFim) {
        List<String> resultado = new ArrayList<>();
        NoFolha atual = primeiraFolha;

        while (atual != null && atual.obterChaves().get(0) < chaveInicio) {
            boolean encontrado = false;
            for (int chave : atual.obterChaves()) {
                if (chave >= chaveInicio) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                atual = atual.obterProximo();
            } else {
                break;
            }
        }

        while (atual != null) {
            for (int i = 0; i < atual.obterChaves().size(); i++) {
                int chave = atual.obterChaves().get(i);
                if (chave >= chaveInicio && chave <= chaveFim) {
                    resultado.add(atual.obterValores().get(i));
                } else if (chave > chaveFim) {
                    return resultado;
                }
            }
            atual = atual.obterProximo();
        }
        return resultado;
    }

    public void imprimirTodos() {
        NoFolha atual = primeiraFolha;
        while (atual != null) {
            for (int i = 0; i < atual.obterChaves().size(); i++) {
                System.out.println("ID: " + atual.obterChaves().get(i) + ", Info: " + atual.obterValores().get(i));
            }
            atual = atual.obterProximo();
        }
    }
}
