public class No {
    int valor;
    int altura;
    No esquerda;
    No direita;

    public No(int valor) {
        this.valor = valor;
        this.altura = 1; // Altura de um novo nó é 1 (folha)
        this.esquerda = null;
        this.direita = null;
    }
}