package list;

import system.Utils;

/**
 * Clase de un nodo doble.
 */
public class NodoDoble {

    /**
     * Elemento guardado
     */
    private Elemento elemento;

    /**
     * El nodo siguiente
     */
    private NodoDoble next;

    /**
     * el nodo anterior
     */
    private NodoDoble back;

    /**
     * CONSTRUCTOR
     * @param elemento elemento a guardar
     */
    public NodoDoble(Elemento elemento) {

        //Validacion del elemento a guardar
        try{
            Utils.validarElemento(elemento);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //se guarda el elemento
        this.elemento = elemento;
    }

    //Los Getter's

    /**
     * metodo que retorna el elemento
     * @return el elemento
     */
    public Elemento getElemento() {
        return elemento;
    }

    /**
     * metodo que retorna el siguiente nodo
     * @return el siguiente nodo
     */
    public NodoDoble getNext() {
        return next;
    }

    /**
     * metodo que retorna el nodo anterior
     * @return el nodo anterior
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
