
public class NoArvoreB {

    Livro[] chaves = new Livro[4];
    NoArvoreB[] filhos = new NoArvoreB[5];
    int numChaves = 0;
    boolean ehFolha = true;

    public boolean estaCheio() {
        return numChaves == 4;
    }

    public void inserirLivro(Livro livro) {
        int i = numChaves - 1;
        while (i >= 0 && chaves[i].isbn > livro.isbn) {
            chaves[i + 1] = chaves[i];
            i--;
        }
        chaves[i + 1] = livro;
        numChaves++;
    }

    public void exibirNo(StringBuilder sb) {
        sb.append("[");
        for (int i = 0; i < numChaves; i++) {
            sb.append(chaves[i].isbn).append(": ").append(chaves[i].titulo);
            if (i < numChaves - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n");
    }
}
