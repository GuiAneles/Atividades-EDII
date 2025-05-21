import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        Random rand = new Random();
        Set<Integer> inseridos = new HashSet<>();

        // Inserir 100 valores únicos
        while (inseridos.size() < 100) {
            int numero = rand.nextInt(1001) - 500; // -500 a 500
            if (inseridos.add(numero)) {
                arvore.inserir(numero);
            }
        }

        StringBuilder insercaoMsg = new StringBuilder("Árvore Após Inserções:\n\n");
        insercaoMsg.append(obterTextoComFator(arvore));
        insercaoMsg.append("\n");
        insercaoMsg.append(arvore.verificarAVL()
            ? "A Árvore É AVL Após As Inserções."
            : "A Árvore Não É AVL Após As Inserções.");

        JOptionPane.showMessageDialog(null, insercaoMsg.toString(), "Inserções", JOptionPane.INFORMATION_MESSAGE);

        // Remover 20 valores aleatórios
        List<Integer> lista = new ArrayList<>(inseridos);
        Collections.shuffle(lista);

        for (int i = 0; i < 20; i++) {
            arvore.remover(lista.get(i));
        }

        StringBuilder remocaoMsg = new StringBuilder("Árvore Após Remoções:\n\n");
        remocaoMsg.append(obterTextoComFator(arvore));
        remocaoMsg.append("\n");
        remocaoMsg.append(arvore.verificarAVL()
            ? "A Árvore É AVL Após As Remoções."
            : "A Árvore Não É AVL Após As Remoções.");

        JOptionPane.showMessageDialog(null, remocaoMsg.toString(), "Remoções", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String obterTextoComFator(ArvoreAVL arvore) {
        StringBuilder sb = new StringBuilder();
        construirTexto(arvore.getRaiz(), sb);
        return sb.toString();
    }

    private static void construirTexto(No no, StringBuilder sb) {
        if (no != null) {
            construirTexto(no.esquerda, sb);
            sb.append("Valor: ").append(no.valor)
              .append(" | Fator De Balanceamento: ")
              .append(fatorBalanceamento(no)).append("\n");
            construirTexto(no.direita, sb);
        }
    }

    private static int fatorBalanceamento(No no) {
        int alturaEsq = (no.esquerda == null) ? 0 : no.esquerda.altura;
        int alturaDir = (no.direita == null) ? 0 : no.direita.altura;
        return alturaDir - alturaEsq;
    }
}
