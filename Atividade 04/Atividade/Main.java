
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String caminhoDoArquivo = "dados100_mil.txt";

        System.out.println("Carregando base de dados do arquivo '" + caminhoDoArquivo + "'...");
        List<Integer> numerosIniciais = GerenciadorDados.carregarNumerosDoArquivo(caminhoDoArquivo);

        if (numerosIniciais.isEmpty()) {
            System.out.println("Não foi possível carregar os dados. Verifique o caminho do arquivo e as permissões. Encerrando.");
            return;
        }

        System.out.println(numerosIniciais.size() + " números carregados para o preenchimento inicial.");

        System.out.println("Gerando conjunto de dados para operações...");
        List<Integer> numerosOperacoes = GerenciadorDados.gerarNumerosOperacoes(50_000);
        System.out.println(numerosOperacoes.size() + " números gerados para as operações mistas.\n");

        System.out.println("--- Iniciando Teste da Árvore Rubro-Negra ---");
        ArvoreRubroNegra arvoreRBT = new ArvoreRubroNegra();

        long inicioPreenchimentoRBT = System.nanoTime();
        for (int num : numerosIniciais) {
            arvoreRBT.inserir(num);
        }
        long fimPreenchimentoRBT = System.nanoTime();
        long duracaoPreenchimentoRBT = (fimPreenchimentoRBT - inicioPreenchimentoRBT) / 1_000_000;

        System.out.println("Preenchimento inicial da RBT concluído.");

        long inicioOperacoesRBT = System.nanoTime();
        for (int num : numerosOperacoes) {
            if (num % 3 == 0) {
                arvoreRBT.inserir(num);
            } else if (num % 5 == 0) {
                arvoreRBT.remover(num);
            } else {
                arvoreRBT.contar(num);
            }
        }
        long fimOperacoesRBT = System.nanoTime();
        long duracaoOperacoesRBT = (fimOperacoesRBT - inicioOperacoesRBT) / 1_000_000;
        System.out.println("Operações mistas na RBT concluídas.\n");

        System.out.println("--- Iniciando Teste da Árvore AVL ---");
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        long inicioPreenchimentoAVL = System.nanoTime();
        for (int num : numerosIniciais) {
            arvoreAVL.inserir(num);
        }
        long fimPreenchimentoAVL = System.nanoTime();
        long duracaoPreenchimentoAVL = (fimPreenchimentoAVL - inicioPreenchimentoAVL) / 1_000_000;

        System.out.println("Preenchimento inicial da AVL concluído.");

        long inicioOperacoesAVL = System.nanoTime();
        for (int num : numerosOperacoes) {
            if (num % 3 == 0) {
                arvoreAVL.inserir(num);
            } else if (num % 5 == 0) {
                arvoreAVL.remover(num);
            } else {
                arvoreAVL.contar(num);
            }
        }
        long fimOperacoesAVL = System.nanoTime();
        long duracaoOperacoesAVL = (fimOperacoesAVL - inicioOperacoesAVL) / 1_000_000;
        System.out.println("Operações mistas na AVL concluídas.\n");

        System.out.println("\n\n--- RESULTADOS DO TESTE DE DESEMPENHO ---");
        System.out.println("=========================================");
        System.out.println("| Árvore        | Tempo de Preenchimento (ms) | Tempo de Operações (ms) |");
        System.out.println("|---------------|-----------------------------|-------------------------|");
        System.out.printf("| Rubro-Negra   | %-27d | %-23d |\n", duracaoPreenchimentoRBT, duracaoOperacoesRBT);
        System.out.printf("| AVL           | %-27d | %-23d |\n", duracaoPreenchimentoAVL, duracaoOperacoesAVL);
    }
}
