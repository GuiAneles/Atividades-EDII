public class ArvoreAVL {
    private No raiz;

    public No getRaiz() {
        return raiz;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    private int altura(No no) {
        return (no == null) ? 0 : no.altura;
    }

    private int fatorBalanceamento(No no) {
        return (no == null) ? 0 : altura(no.direita) - altura(no.esquerda);
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    private No balancear(No no) {
        atualizarAltura(no);
        int fb = fatorBalanceamento(no);

        if (fb < -1) {
            if (fatorBalanceamento(no.esquerda) > 0) {
                no.esquerda = rotacaoEsquerda(no.esquerda);
            }
            return rotacaoDireita(no);
        }

        if (fb > 1) {
            if (fatorBalanceamento(no.direita) < 0) {
                no.direita = rotacaoDireita(no.direita);
            }
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No inserir(No no, int valor) {
        if (no == null) return new No(valor);

        if (valor < no.valor)
            no.esquerda = inserir(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserir(no.direita, valor);
        else
            return no; // Duplicados não permitidos

        return balancear(no);
    }

    private No minimo(No no) {
        while (no.esquerda != null)
            no = no.esquerda;
        return no;
    }

    private No remover(No no, int valor) {
        if (no == null) return null;

        if (valor < no.valor) {
            no.esquerda = remover(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = remover(no.direita, valor);
        } else {
            if (no.esquerda == null || no.direita == null) {
                no = (no.esquerda != null) ? no.esquerda : no.direita;
            } else {
                No temp = minimo(no.direita);
                no.valor = temp.valor;
                no.direita = remover(no.direita, temp.valor);
            }
        }

        return (no != null) ? balancear(no) : null;
    }

    public void imprimirComFB() {
        imprimirComFB(raiz);
    }

    private void imprimirComFB(No no) {
        if (no != null) {
            imprimirComFB(no.esquerda);
            System.out.println("Valor: " + no.valor + " | FB: " + fatorBalanceamento(no));
            imprimirComFB(no.direita);
        }
    }

    public boolean verificarAVL() {
        return verificarAVL(raiz);
    }

    private boolean verificarAVL(No no) {
        if (no == null) return true;

        int fb = fatorBalanceamento(no);
        if (fb < -1 || fb > 1) return false;

        return verificarAVL(no.esquerda) && verificarAVL(no.direita);
    }
}
