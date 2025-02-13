package list;

import system.Utils;

/**
 * Clase que almacena un conjunto de nodos.
 */
public class ListaNodoDoble implements Lista{

    /**
     * La cabeza de los nodos
     */
    private NodoDoble head;

    /**
     * la cola de los nodos
     */
    private NodoDoble tail;

    /**
     * cantidad total de nodos en la lista
     */
    private int cantNodos;

    /**
     * CONSTRUCTOR
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

    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento a eliminar.
     * @return true si el elemento fue borrado, false en caso contrario.
     */
    @Override
    public boolean eliminar(Elemento elemento) {
        //Valida el elemento.
        try{
            Utils.validarElemento(elemento);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Verifica si la lista esta vacía.
        if(this.isVacia()){
            return false;
        }

        //En caso de que el elemento a eliminar se encuentre en la cabeza.
        if(this.head.getElemento().esIgual(elemento)){
            this.head=this.head.getNext();
            this.cantNodos--;
            return true;
        }

        //En caso de que el elemento a eliminar se encuentre en la cola.
        if(this.tail.getElemento().esIgual(elemento)){
            this.tail=this.tail.getBack();
            this.cantNodos--;
            return true;
        }

        //En cualquier otro caso.
        for(NodoDoble aux = this.head; aux!=null; aux=aux.getNext()){
            NodoDoble ant = aux.getBack();
            NodoDoble sig = aux.getNext();

            if(aux.getElemento().esIgual(elemento)){
                ant.setNext(sig);
                sig.setBack(ant);
                this.cantNodos--;
                return true;
            }
        }

        //El elemento no fue encontrado o no existe.
        return false;
    }

    /**
     * Método que verifica si la lista contiene un elemento o no.
     * @param elemento a verificar.
     * @return true si el elemento es contenido en un nodo, false en caso contrario.
     */
    @Override
    public boolean contiene(Elemento elemento) {

        //primero se verifica si el elemento es valido
        try{
            Utils.validarElemento(elemento);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //se busca en la lista el elemento a buscar
        for(NodoDoble aux = this.head; aux!=null; aux=aux.getNext()){
            if(aux.getElemento().esIgual(elemento)){
                return true;
            }
        }

        return false;
    }

    /**
     * metodo que vacía la lista de nodos
     */
    @Override
    public void vaciar() {this.head=null;}

    /**
     * metodo que verifica si la lista está vacia
     * @return true si está vacia, false si no
     */
    @Override
    public boolean isVacia() {return this.head==null;}

    /**
     * metodo que agrega un nodo por posicion
     * @param posicion la posicion donde se quiere agregar el elemento
     * @param elemento elemento del nodo
     * @return true si se pudo, false si no
     */
    @Override
    public boolean agregar(int posicion, Elemento elemento) {
        //Valida el valor de la posición.
        try{
            Utils.validarNumero(posicion,0,this.cantNodos);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Validar el elemento
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

        //Si la posición es 0.
        if(posicion==0){
            this.head.setBack(nuevo);
            nuevo.setNext(this.head);
            this.head=nuevo;
            this.cantNodos++;
            return true;
        }

        //Si la posición es la última.
        if(posicion==this.cantNodos){
            this.tail.setNext(nuevo);
            nuevo.setBack(this.tail);
            this.tail=nuevo;
            this.cantNodos++;
            return true;
        }

        //Cualquier otro caso.
        int auxPos=0;
        for(NodoDoble aux=this.head; aux.getNext()!=null; aux=aux.getNext()){
            if(auxPos==posicion){
                aux.setNext(nuevo);
                nuevo.setBack(aux);
                nuevo.setNext(aux.getNext().getNext());
                this.cantNodos++;
                return true;
            }

            auxPos++;
        }

        return false;
    }

    /**
     * metodo que elimina un nodo por posicion
     * @param posicion posicion del nodo a eliminar
     * @return true si se pudo, false si no
     */
    @Override
    public boolean eliminar(int posicion) {
        //Valida la posición.
        try {
            Utils.validarNumero(posicion,0,this.cantNodos-1);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Si la posición a eliminar es la primera.
        if(posicion==0){
            this.head.getNext().setBack(null);
            this.head.setNext(null);
            this.head=this.head.getNext();
            this.cantNodos--;
            return true;
        }

        //Si la posición a eliminar es la última.
        if(posicion==this.cantNodos-1){
            this.tail.getBack().setNext(null);
            this.tail.setBack(null);
            this.tail=this.tail.getBack();
            this.cantNodos--;
            return true;
        }

        //Si la posición está en medio.
        int auxPos=0;
        for(NodoDoble aux = this.head; aux!=null; aux=aux.getNext()){
            NodoDoble ant=aux.getBack();
            NodoDoble sig=aux.getNext();
            if(auxPos==posicion){
                ant.setNext(sig);
                sig.setBack(ant);
                this.cantNodos--;
                return true;
            }
            auxPos++;
        }

        //Posición no encontrada.
        return false;
    }

    /**
     * Método para hallar la posición de un elemento.
     * @param elemento a buscar su posición.
     * @return la posición del elemento buscado.
     */
    @Override
    public int posicionDe(Elemento elemento) {
        //Valida el elemento.
        try {
            Utils.validarElemento(elemento);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        int auxPos=0;

        //Busca el elemento.
        for(NodoDoble aux=this.head; aux!=null; aux=aux.getNext()){
            if(aux.getElemento().esIgual(elemento)){
                //Se encontró el elemento y retorna su posición.
                return auxPos;
            }

            auxPos++;
        }

        //No se encontró el elemento.
        return -1;
    }

    /**
     * metodo que obtiene un elemento segun la posicion asignada
     * @param posicion posicion del elemento a obtener
     * @return el elemento
     */
    @Override
    public Elemento obtener(int posicion) {
        //Valida la posición.
        try {
            Utils.validarNumero(posicion,0,this.cantNodos-1);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Recorre la lista.
        int auxPos=0;
        for(NodoDoble aux=this.head; aux!=null; aux=aux.getNext()){
            if(auxPos==posicion){
                //Encontró el elemento en la posición dada.
                return aux.getElemento();
            }

            auxPos++;
        }

        return null;
    }

    //Los Getter's

    /**
     * metodo que retorna la cabeza
     * @return la cabeza
     */
    public NodoDoble getHead() {
        return head;
    }

    /**
     * metodo que retorna la cola
     * @return la cola
     */
    public NodoDoble getTail() {
        return tail;
    }

    /**
     * metodo que retorna la cantidad total de nodos
     * @return cantidad actual de nodos
     */
    public int getCantNodos() {
        return cantNodos;
    }
}