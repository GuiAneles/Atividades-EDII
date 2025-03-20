import java.util.ArrayList;
import java.util.List;

class Pilha<T> {
    private List<T> elementos;

    public Pilha() {
        this.elementos = new ArrayList<>();
    }

    public void push(T elemento) {
        elementos.add(elemento);
    }

    public T pop() {
        if (is_empty()) {
            return null;
        }
        return elementos.remove(elementos.size() - 1);
    }

    public T peek() {
        if (is_empty()) {
            return null;
        }
        return elementos.get(elementos.size() - 1);
    }

    public boolean is_empty() {
        return elementos.isEmpty();
    }
}