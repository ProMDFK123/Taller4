package list;

/**
 * Interface Elemento
 */
public interface Elemento {

    /**
     * metodo que verifica si el elemento es igual a otro
     * @param elemento elemento ingresado
     * @return true si son iguales, false si no
     */
    boolean esIgual(Elemento elemento);

    /**
     * metodo que compara un elemento con otro
     * @param elemento elementro ingresado
     * @return true si son iguales, false si no
     */
    int compararCon(Elemento elemento);
}
