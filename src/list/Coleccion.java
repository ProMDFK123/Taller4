package list;

/**
 * Interface coleccion
 */
public interface Coleccion {

    /**
     * metodo que agrega un elemento
     * @param elemento elementro a agregar
     * @return true si se puede, false si no
     */
    boolean agregar(Elemento elemento);

    /**
     * metodo que elimina un elemento
     * @param elemento elemento a eliminar
     * @return true si se pudo, false si no
     */
    boolean eliminar(Elemento elemento);

    /**
     * metodo que verifica si un nodo contiene el elemento dado
     * @param elemento elemento a buscar
     * @return true si se pudo, false si no
     */
    boolean contiene(Elemento elemento);

    /**
     * metodo que vacía la lista de nodos
     */
    void vaciar();

    /**
     * metodo que verifica si la lista está vacia
     * @return true si esta vacia, false si no
     */
    boolean isVacia();
}