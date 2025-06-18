
public class ArvoreAVL {

    private NoAVL raiz;

    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    private int maximo(int a, int b) {
        return Math.max(a, b);
    }

    private int obterFatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return altura(no.direita) - altura(no.esquerda);
    }

    private void atualizarAltura(NoAVL no) {
        no.altura = 1 + maximo(altura(no.esquerda), altura(no.direita));
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private NoAVL inserir(NoAVL no, int valor) {
        if (no == null) {
            return new NoAVL(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserir(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserir(no.direita, valor);
        } else {
            return no;
        }

        atualizarAltura(no);
        int fatorBalanceamento = obterFatorBalanceamento(no);

        if (fatorBalanceamento < -1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }

        if (fatorBalanceamento > 1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }

        if (fatorBalanceamento < -1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (fatorBalanceamento > 1 && valor < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    private NoAVL remover(NoAVL no, int valor) {
        if (no == null) {
            return no;
        }

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            if ((no.esquerda == null) || (no.direita == null)) {
                no = (no.esquerda != null) ? no.esquerda : no.direita;
            } else {
                NoAVL temporario = encontrarMenorValor(no.direita);
                no.valor = temporario.valor;
                no.direita = remover(no.direita, temporario.valor);
            }
        }

        if (no == null) {
            return no;
        }

        atualizarAltura(no);
        int fatorBalanceamento = obterFatorBalanceamento(no);

        if (fatorBalanceamento < -1 && obterFatorBalanceamento(no.esquerda) <= 0) {
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento < -1 && obterFatorBalanceamento(no.esquerda) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (fatorBalanceamento > 1 && obterFatorBalanceamento(no.direita) >= 0) {
            return rotacaoEsquerda(no);
        }
        if (fatorBalanceamento > 1 && obterFatorBalanceamento(no.direita) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private NoAVL encontrarMenorValor(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public int contar(int valor) {
        return contarRecursivo(this.raiz, valor);
    }

    private int contarRecursivo(NoAVL no, int valor) {
        if (no == null) {
            return 0;
        }
        if (valor < no.valor) {
            return contarRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            return contarRecursivo(no.direita, valor);
        } else {
            return 1;
        }
    }
}
