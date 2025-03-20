class TrabalhoImpressao {
    private int idAluno;
    private String nomeArquivo;
    private int numeroPaginas;

    public TrabalhoImpressao(int idAluno, String nomeArquivo, int numeroPaginas) {
        this.idAluno = idAluno;
        this.nomeArquivo = nomeArquivo;
        this.numeroPaginas = numeroPaginas;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String toString() {
        return "ID Aluno: " + idAluno + ", Arquivo: " + nomeArquivo + ", Paginas: " + numeroPaginas;
    }
}