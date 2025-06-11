
public class ArvoreB {

    private NoArvoreB raiz = new NoArvoreB();

    public void inserir(Livro livro) {
        if (raiz.estaCheio()) {
            NoArvoreB novaRaiz = new NoArvoreB();
            novaRaiz.ehFolha = false;
            novaRaiz.filhos[0] = raiz;
            dividirFilho(novaRaiz, 0);
            raiz = novaRaiz;
        }
        inserirNaoCheio(raiz, livro);
    }

    private void inserirNaoCheio(NoArvoreB no, Livro livro) {
        int i = no.numChaves - 1;
        if (no.ehFolha) {
            no.inserirLivro(livro);
        } else {
            while (i >= 0 && livro.isbn < no.chaves[i].isbn) {
                i--;
            }
            i++;
            if (no.filhos[i].estaCheio()) {
                dividirFilho(no, i);
                if (livro.isbn > no.chaves[i].isbn) {
                    i++;
                }
            }
            inserirNaoCheio(no.filhos[i], livro);
        }
    }

    private void dividirFilho(NoArvoreB pai, int indice) {
        NoArvoreB cheio = pai.filhos[indice];
        NoArvoreB novo = new NoArvoreB();
        novo.ehFolha = cheio.ehFolha;
        novo.numChaves = 2;

        for (int j = 0; j < 2; j++) {
            novo.chaves[j] = cheio.chaves[j + 2];
        }

        if (!cheio.ehFolha) {
            for (int j = 0; j < 3; j++) {
                novo.filhos[j] = cheio.filhos[j + 2];
            }
        }

        cheio.numChaves = 2;

        for (int j = pai.numChaves; j >= indice + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }

        pai.filhos[indice + 1] = novo;

        for (int j = pai.numChaves - 1; j >= indice; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        pai.chaves[indice] = cheio.chaves[2];
        pai.numChaves++;
    }

    public boolean buscar(int isbn) {
        return buscarNo(raiz, isbn);
    }

    private boolean buscarNo(NoArvoreB no, int isbn) {
        int i = 0;
        while (i < no.numChaves && isbn > no.chaves[i].isbn) {
            i++;
        }
        if (i < no.numChaves && isbn == no.chaves[i].isbn) {
            javax.swing.JOptionPane.showMessageDialog(null, "Livro encontrado:\n" + no.chaves[i]);
            return true;
        }
        if (no.ehFolha) {
            return false;
        }
        return buscarNo(no.filhos[i], isbn);
    }

    public String getLivrosEmOrdemPorTitulo() {
        StringBuilder sb = new StringBuilder();
        listarLivros(raiz, sb);
        return sb.toString();
    }

    private void listarLivros(NoArvoreB no, StringBuilder sb) {
        for (int i = 0; i < no.numChaves; i++) {
            if (!no.ehFolha) {
                listarLivros(no.filhos[i], sb);
            }
            sb.append(no.chaves[i]).append("\n");
        }
        if (!no.ehFolha) {
            listarLivros(no.filhos[no.numChaves], sb);
        }
    }

    public String getEstrutura() {
        StringBuilder sb = new StringBuilder();
        construirEstrutura(raiz, 0, sb);
        return sb.toString();
    }

    private void construirEstrutura(NoArvoreB no, int nivel, StringBuilder sb) {
        sb.append("  ".repeat(nivel));
        no.exibirNo(sb);
        if (!no.ehFolha) {
            for (int i = 0; i <= no.numChaves; i++) {
                construirEstrutura(no.filhos[i], nivel + 1, sb);
            }
        }
    }
}
