
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorDados {

    public static List<Integer> carregarNumerosDoArquivo(String nomeArquivo) {
        List<Integer> numeros = new ArrayList<>();
        StringBuilder conteudoArquivo = new StringBuilder();

        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudoArquivo.append(linha).append(" ");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de dados: " + nomeArquivo);
            e.printStackTrace();
            return numeros;
        }

        String[] partes = conteudoArquivo.toString().trim().split("[,\\s]+");

        for (String parte : partes) {
            if (!parte.isEmpty()) {
                try {
                    numeros.add(Integer.parseInt(parte));
                } catch (NumberFormatException e) {
                    // Ignora silenciosamente tokens não-numéricos
                }
            }
        }
        return numeros;
    }

    public static List<Integer> gerarNumerosOperacoes(int quantidade) {
        List<Integer> numeros = new ArrayList<>();
        Random aleatorio = new Random();
        for (int i = 0; i < quantidade; i++) {
            numeros.add(aleatorio.nextInt(19999) - 9999);
        }
        return numeros;
    }
}
