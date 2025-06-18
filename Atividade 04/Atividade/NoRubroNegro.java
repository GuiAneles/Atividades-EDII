
public class NoRubroNegro {

    int valor;
    boolean ehVermelho;
    NoRubroNegro pai;
    NoRubroNegro esquerda;
    NoRubroNegro direita;

    public NoRubroNegro(int valor) {
        this.valor = valor;
        this.ehVermelho = true;
        this.pai = null;
        this.esquerda = null;
        this.direita = null;
    }
}
