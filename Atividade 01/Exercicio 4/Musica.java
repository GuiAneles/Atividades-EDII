class Musica {
    String titulo;
    String artista;
    Musica proxima;

    public Musica(String titulo, String artista) {
        this.titulo = titulo;
        this.artista = artista;
        this.proxima = null;
    }
}