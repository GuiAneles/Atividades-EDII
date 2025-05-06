import java.util.*;

public class ArvoreBinaria {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserirNo(raiz, valor);
    }

    private No inserirNo(No no, int valor) {
        if (no == null) return new No(valor);
        if (valor < no.valor)
            no.esquerda = inserirNo(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = inserirNo(no.direita, valor);
        return no;
    }

    public void remover(int valor) {
        raiz = removerNo(raiz, valor);
    }

    private No removerNo(No no, int valor) {
        if (no == null) return null;

        if (valor < no.valor)
            no.esquerda = removerNo(no.esquerda, valor);
        else if (valor > no.valor)
            no.direita = removerNo(no.direita, valor);
        else {
            if (no.esquerda == null)
                return no.direita;
            else if (no.direita == null)
                return no.esquerda;

            No temp = minimoValorNo(no.direita);
            no.valor = temp.valor;
            no.direita = removerNo(no.direita, temp.valor);
        }
        return no;
    }

    private No minimoValorNo(No no) {
        while (no.esquerda != null)
            no = no.esquerda;
        return no;
    }

    public void imprimirPreOrdem() {
        System.out.print("Pre-Ordem: ");
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    public void imprimirInOrdem() {
        System.out.print("In-Ordem: ");
        inOrdem(raiz);
        System.out.println();
    }

    private void inOrdem(No no) {
        if (no != null) {
            inOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            inOrdem(no.direita);
        }
    }

    public void imprimirPosOrdem() {
        System.out.print("Pos-Ordem: ");
        posOrdem(raiz);
        System.out.println();
    }

    private void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    public void imprimirEmNivel() {
        System.out.print("Em Nivel: ");
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.valor + " ");
            if (atual.esquerda != null)
                fila.add(atual.esquerda);
            if (atual.direita != null)
                fila.add(atual.direita);
        }
        System.out.println();
    }
}
