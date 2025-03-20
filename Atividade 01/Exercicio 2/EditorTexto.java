class EditorTexto {
    private StringBuilder texto;
    private Pilha<Operacao> pilhaDesfazer;
    private Pilha<Operacao> pilhaRefazer;

    public EditorTexto() {
        this.texto = new StringBuilder();
        this.pilhaDesfazer = new Pilha<>();
        this.pilhaRefazer = new Pilha<>();
    }

    public void inserir(String textoInserido) {
        texto.append(textoInserido);
        pilhaDesfazer.push(new Operacao("INSERIR", textoInserido));
        pilhaRefazer = new Pilha<>();
    }

    public void remover(int n) {
        if (n > texto.length()) {
            n = texto.length();
        }
        String textoRemovido = texto.substring(texto.length() - n);
        texto.delete(texto.length() - n, texto.length());
        pilhaDesfazer.push(new Operacao("REMOVER", textoRemovido));
        pilhaRefazer = new Pilha<>(); 
    }

    public void desfazer() {
        if (pilhaDesfazer.is_empty()) {
            System.out.println("Nada para desfazer.");
            return;
        }
        Operacao ultimaOperacao = pilhaDesfazer.pop();
        if (ultimaOperacao.getTipo().equals("INSERIR")) {
            String textoInserido = ultimaOperacao.getTexto();
            texto.delete(texto.length() - textoInserido.length(), texto.length());
            pilhaRefazer.push(new Operacao("REMOVER", textoInserido));
        } else if (ultimaOperacao.getTipo().equals("REMOVER")) {
            String textoRemovido = ultimaOperacao.getTexto();
            texto.append(textoRemovido);
            pilhaRefazer.push(new Operacao("INSERIR", textoRemovido));
        }
    }

    public void refazer() {
        if (pilhaRefazer.is_empty()) {
            System.out.println("Nada para refazer.");
            return;
        }
        Operacao ultimaOperacao = pilhaRefazer.pop();
        if (ultimaOperacao.getTipo().equals("INSERIR")) {
            texto.append(ultimaOperacao.getTexto());
            pilhaDesfazer.push(new Operacao("INSERIR", ultimaOperacao.getTexto()));
        } else if (ultimaOperacao.getTipo().equals("REMOVER")) {
            String textoRemovido = ultimaOperacao.getTexto();
            texto.delete(texto.length() - textoRemovido.length(), texto.length());
            pilhaDesfazer.push(new Operacao("REMOVER", textoRemovido));
        }
    }

    public void imprimir() {
        System.out.println(">> " + texto.toString());
    }
}