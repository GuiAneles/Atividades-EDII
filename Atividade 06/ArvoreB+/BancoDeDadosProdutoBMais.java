
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BancoDeDadosProdutoBMais {

    private ArvoreBMais indiceProduto;
    private static final int ORDEM = 3;

    public BancoDeDadosProdutoBMais() {
        this.indiceProduto = new ArvoreBMais(ORDEM);
    }

    public void carregarProdutosDoArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(","); // Declarar 'parts' aqui
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String nome = parts[1];
                    String categoria = parts[2];
                    indiceProduto.inserir(id, new Produto(id, nome, categoria).toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encontrarProduto(int idProduto) {
        return indiceProduto.buscar(idProduto);
    }

    public boolean removerProduto(int idProduto) {
        return indiceProduto.remover(idProduto);
    }

    public static void main(String[] args) {
        BancoDeDadosProdutoBMais bd = new BancoDeDadosProdutoBMais();
        String nomeArquivo = "produtos.txt";

        System.out.println("--- Implementação da Árvore B+ ---");

        long tempoInicio = System.nanoTime();
        bd.carregarProdutosDoArquivo(nomeArquivo);
        long tempoFim = System.nanoTime();
        long duracao = (tempoFim - tempoInicio) / 1_000_000;
        System.out.println("Tempo gasto para inserção: " + duracao + " ms");

        System.out.println("\nTentando remover 10 produtos aleatórios (IDs entre 1000 e 2000):");
        Random rand = new Random();
        List<Integer> chavesParaRemover = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            chavesParaRemover.add(rand.nextInt(1001) + 1000);
        }

        tempoInicio = System.nanoTime();
        for (int chave : chavesParaRemover) {
            String infoProduto = bd.encontrarProduto(chave);
            if (infoProduto != null) {
                System.out.println("Produto com ID " + chave + " encontrado: " + infoProduto + ". Tentando remover.");
                if (bd.removerProduto(chave)) {
                    System.out.println("Produto com ID " + chave + " removido com sucesso.");
                } else {
                    System.out.println("Falha ao remover produto com ID " + chave + ".");
                }
            } else {
                System.out.println("Produto com ID " + chave + " não encontrado.");
            }
        }
        tempoFim = System.nanoTime();
        duracao = (tempoFim - tempoInicio) / 1_000_000;
        System.out.println("Tempo gasto para remoções: " + duracao + " ms");

        System.out.println("\n--- Árvore B+: Todos os produtos restantes (lista parcial) ---");
        List<String> produtosRestantes = bd.indiceProduto.buscarIntervalo(1000, 1020);
        System.out.println("Produtos com ID entre 1000 e 1020 após as remoções:");
        for (String produto : produtosRestantes) {
            System.out.println("- " + produto);
        }
    }
}
