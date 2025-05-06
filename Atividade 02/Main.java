import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Random rand = new Random();
        List<Integer> numeros = new ArrayList<>();

        System.out.println("Numeros Sorteados E Inseridos:");
        while (numeros.size() < 20) {
            int n = rand.nextInt(101);
            if (!numeros.contains(n)) {
                numeros.add(n);
                arvore.inserir(n);
                System.out.print(n + " ");
            }
        }
        System.out.println("\n");

        System.out.println("Impressoes Antes Da Remocao:");
        arvore.imprimirPreOrdem();
        arvore.imprimirInOrdem();
        arvore.imprimirPosOrdem();
        arvore.imprimirEmNivel();

        System.out.println("\nRemovendo Os 5 Primeiros Valores: " + numeros.subList(0, 5));
        for (int i = 0; i < 5; i++) {
            arvore.remover(numeros.get(i));
        }

        System.out.println("\nImpressoes Apos Remocao:");
        arvore.imprimirPreOrdem();
        arvore.imprimirInOrdem();
        arvore.imprimirPosOrdem();
        arvore.imprimirEmNivel();
    }
}
