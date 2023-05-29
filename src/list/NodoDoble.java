package list;

import system.Utils;

/**
 * Clase de un nodo doble.
 */
public class NodoDoble {
    //Elemento a guardar.
    private Elemento elemento;

    //Nodo siguiente.
    private NodoDoble next;

    //Nodo anterior.
    private NodoDoble back;

    public NodoDoble(Elemento elemento) {
        try{
            Utils.validarElemento(elemento);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }
        this.elemento = elemento;
    }

    //Los Getter's

    /**
     * @return el elemento almacenado.
     */
    public Elemento getElemento() {
        return elemento;
    }

    /**
     * @return el nodo siguiente.
     */
    public NodoDoble getNext() {
        return next;
    }

    /**
     * @return el nodo anterior.
     */
    public NodoDoble getBack() {
        return back;
    }

    //Los Setter's

    /**
     * Establece un nuevo nodo siguiente.
     * @param next - nuevo nodo siguiente.
     */
    public void setNext(NodoDoble next) {
        this.next = next;
    }

    /**
     * Establece un nuevo nodo anterior.
     * @param back - nuevo nodo anterior.
     */
    public void setBack(NodoDoble back) {
        this.back = back;
    }
}
