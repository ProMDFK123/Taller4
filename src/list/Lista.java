package list;

public interface Lista extends Coleccion {
    boolean agregar(int posicion, Elemento elemento);
    boolean eliminar(int posicion);
    int posicionDe(Elemento elemento);
    Elemento obtener(int posicion);
}
