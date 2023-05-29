package list;

public interface Coleccion {
    boolean agregar(Elemento elemento);
    boolean eliminar(Elemento elemento);
    boolean contiene(Elemento elemento);
    void vaciar();
    boolean isVacia();
}
