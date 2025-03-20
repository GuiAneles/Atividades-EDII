class Playlist {
    private Musica inicio;

    public Playlist() {
        this.inicio = null;
    }

    public void AdicionarMusica(String titulo, String artista) {
        Musica novaMusica = new Musica(titulo, artista);
        if (inicio == null) {
            inicio = novaMusica;
        } else {
            Musica atual = inicio;
            while (atual.proxima != null) {
                atual = atual.proxima;
            }
            atual.proxima = novaMusica;
        }
        System.out.println("Musica Adicionada: " + titulo + " - " + artista);
    }

    public void RemoverMusica(String titulo) {
        if (inicio == null) {
            System.out.println("Playlist Vazia.");
            return;
        }

        if (inicio.titulo.equalsIgnoreCase(titulo)) {
            inicio = inicio.proxima;
            System.out.println("Musica Removida: " + titulo);
            return;
        }

        Musica atual = inicio;
        while (atual.proxima != null && !atual.proxima.titulo.equalsIgnoreCase(titulo)) {
            atual = atual.proxima;
        }

        if (atual.proxima != null) {
            atual.proxima = atual.proxima.proxima;
            System.out.println("Musica Removida: " + titulo);
        } else {
            System.out.println("Musica Nao Encontrada: " + titulo);
        }
    }

    public void BuscarMusica(String titulo) {
        Musica atual = inicio;
        while (atual != null) {
            if (atual.titulo.equalsIgnoreCase(titulo)) {
                System.out.println("Musica Encontrada: " + atual.titulo + " - " + atual.artista);
                return;
            }
            atual = atual.proxima;
        }
        System.out.println("Musica Nao Encontrada: " + titulo);
    }

    public void ListarMusicas() {
        if (inicio == null) {
            System.out.println("Playlist Vazia.");
            return;
        }

        Musica atual = inicio;
        System.out.println("Playlist:");
        while (atual != null) {
            System.out.println(atual.titulo + " - " + atual.artista);
            atual = atual.proxima;
        }
    }
}