public class TesteLabirinto {

    public static boolean ResolverLabirinto(char[][] labirinto) {
        int linhas = labirinto.length;
        int colunas = labirinto[0].length;

        int entradaX = -1, entradaY = -1;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (labirinto[i][j] == 'E') {
                    entradaX = i;
                    entradaY = j;
                    break;
                }
            }
            if (entradaX != -1) break;
        }

        if (entradaX == -1 || entradaY == -1) {
            System.out.println("Entrada Nao Encontrada No Labirinto.");
            return false;
        }

        boolean[][] visitados = new boolean[linhas][colunas];

        boolean resultado = ExplorarLabirinto(labirinto, visitados, entradaX, entradaY);

        if (resultado) {
            System.out.println("Caminho Encontrado:");
            ExibirLabirinto(labirinto);
        } else {
            System.out.println("Labirinto Sem Saida.");
        }

        return resultado;
    }

    private static boolean ExplorarLabirinto(char[][] labirinto, boolean[][] visitados, int x, int y) {
        if (x < 0 || x >= labirinto.length || y < 0 || y >= labirinto[0].length) {
            return false; 
        }

        if (labirinto[x][y] == '#' || visitados[x][y]) {
            return false;
        }

        if (labirinto[x][y] == 'S') {
            return true;
        }

        visitados[x][y] = true;

        if (labirinto[x][y] != 'E') {
            labirinto[x][y] = '*';
        }

        if (ExplorarLabirinto(labirinto, visitados, x + 1, y)) return true; // Baixo
        if (ExplorarLabirinto(labirinto, visitados, x - 1, y)) return true; // Cima
        if (ExplorarLabirinto(labirinto, visitados, x, y + 1)) return true; // Direita
        if (ExplorarLabirinto(labirinto, visitados, x, y - 1)) return true; // Esquerda

        if (labirinto[x][y] != 'E') {
            labirinto[x][y] = '.';
        }

        return false; 
    }

    private static void ExibirLabirinto(char[][] labirinto) {
        for (char[] linha : labirinto) {
            for (char celula : linha) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] labirinto = {
            {'#', '#', '#', '#', '#'},
            {'#', 'E', '.', '.', '#'},
            {'#', '#', '#', '.', '#'},
            {'#', 'S', '.', '.', '#'},
            {'#', '#', '#', '#', '#'}
        };

        ResolverLabirinto(labirinto);
    }
}