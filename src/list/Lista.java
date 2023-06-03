package list;

/**
 * interface lista (interface hija de coleccion)
 */
public interface Lista extends Coleccion {

    /**
     * metodo que agrega un elemento segun la posicion dada
     * @param posicion posicion donde agregar el elemento
     * @param elemento elemento  a agregar
     * @return true si se pudo, false si no
     */
    boolean agregar(int posicion, Elemento elemento);

    /**
     * metodo que elimina un nodo por posicion
     * @param posicion posicion a eliminar
     * @return true si se pudo, false si no
     */
    boolean eliminar(int posicion);

    /**
     * metodo que halla la posicion de un elemento
     * @param elemento elemento a buscar posicion
     * @return posicion del elemento, -1 si no se encontró
     */
    int posicionDe(Elemento elemento);

    /**
     * metodo que obtiene un elemento segun su posicion
     * @param posicion posicion del elemento a obtener
     * @return  el elemento, null si no se encontró
     */
    Elemento obtener(int posicion);

}