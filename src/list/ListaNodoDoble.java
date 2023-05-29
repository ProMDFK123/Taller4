package list;

import system.Utils;

/**
 * Clase que almacena un conjunto de nodos.
 */
public class ListaNodoDoble implements Lista{
    //Primer nodo de la lista.
    private NodoDoble head;

    //Ultimo nodo de la lista.
    private NodoDoble tail;

    //Cantidad de elementos en la lista.
    private int cantNodos;

    /**
     * Constructor de una lista.
     */
    public ListaNodoDoble() {
        this.head=null;
        this.tail=null;
        this.cantNodos=0;
    }

    /**
     * Agrega un nuevo elemento a la lista.
     * @param elemento a agregar
     * @return true si se agregó, false en caso contrario.
     */
    @Override
    public boolean agregar(Elemento elemento) {
        //Valida el elemento.
        try{
            Utils.validarElemento(elemento);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Crea un nodo con el elemento.
        NodoDoble nuevo = new NodoDoble(elemento);

        //Revisa si la lista esta vacía.
        if(this.isVacia()){
            //Agrega el nodo.
            this.head=nuevo;
            this.tail=nuevo;
            this.tail.setBack(nuevo);
            this.head.setNext(nuevo);
            this.cantNodos++;
            return true;
        }

        //Agregar al final.
        this.tail.setNext(nuevo);
        nuevo.setBack(this.tail);
        this.tail=nuevo;
        this.cantNodos++;
        return true;
    }

    @Override
    public boolean eliminar(Elemento elemento) {
        if(this.isVacia()){
            return false;
        }

        if(this.head.getElemento().esIgual(elemento)){
            this.head=this.head.getNext();
            this.cantNodos--;
            return true;
        }

        if(this.tail.getElemento().esIgual(elemento)){
            this.tail=this.tail.getBack();
            this.cantNodos--;
            return true;
        }

        for(NodoDoble aux = this.head; )
    }

    @Override
    public boolean contiene(Elemento elemento) {
        return false;
    }

    @Override
    public void vaciar() {

    }

    @Override
    public boolean isVacia() {
        return false;
    }

    @Override
    public boolean agregar(int posicion) {
        return false;
    }

    @Override
    public boolean eliminar(int posicion) {
        return false;
    }

    @Override
    public int posicionDe(Elemento elemento) {
        return 0;
    }

    @Override
    public Elemento obtener(int posicion) {
        return null;
    }
}
