import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        while (true) {
            String[] opcoes = {
                "Adicionar Musica",
                "Remover Musica",
                "Buscar Musica",
                "Listar Musicas",
                "Sair"
            };

            int opcao = JOptionPane.showOptionDialog(
                null,
                "Escolha Uma Opcao:",
                "Gerenciador De Playlist",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (opcao) {
                case 0: 
                    String titulo = JOptionPane.showInputDialog("Digite O Titulo Da Musica:");
                    String artista = JOptionPane.showInputDialog("Digite O Nome Do Artista:");
                    if (titulo != null && artista != null) {
                        playlist.AdicionarMusica(titulo, artista);
                    }
                    break;

                case 1:
                    titulo = JOptionPane.showInputDialog("Digite O Titulo Da Musica A Ser Removida:");
                    if (titulo != null) {
                        playlist.RemoverMusica(titulo);
                    }
                    break;

                case 2:
                    titulo = JOptionPane.showInputDialog("Digite O Titulo Da Musica A Ser Buscada:");
                    if (titulo != null) {
                        playlist.BuscarMusica(titulo);
                    }
                    break;

                case 3:
                    playlist.ListarMusicas();
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opcao Invalida.");
            }
        }
    }
}