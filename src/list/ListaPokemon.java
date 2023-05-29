package list;

import objects.Pokemon;
import system.Utils;

/**
 * Clase auxiliar para ordenamientos.
 */
public class ListaPokemon {
    //Lista de Pokémon.
    private Pokemon[] lista;

    //Cantidad actual de elementos.
    private int actual;

    //Cantidad máxima de elementos.
    private int max;

    /**
     * Constructor de una lista.
     *
     * @param max - capacidad máxima de la lista.
     */
    public ListaPokemon(int max) {
        this.max = max;
        this.actual = 0;
        this.lista = new Pokemon[max];
    }

    //Los Getter's

    /**
     * @return la cantidad actual.
     */
    public int getActual() {
        return actual;
    }

    /**
     * @return la cantidad máxima.
     */
    public int getMax() {
        return max;
    }

    /**
     * Agrega un nuevo Pokémon.
     * @param pokemon a agregar.
     */
    public void agregar(Pokemon pokemon) {
        //Valida el elemento.
        try{
            Utils.validarElemento(pokemon);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        this.lista[this.actual] = pokemon;
        this.actual++;
    }

    /**
     * Elimina un elemento.
     * @param pokemon a eliminar.
     */
    public void eliminar(Pokemon pokemon){
        //Valida el elemento.
        try{
            Utils.validarElemento(pokemon);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Recorre la lista.
        for(int i=0; i<this.actual;i++){
            //Verifica el elemento en la posición i.
            if(this.lista[i].esIgual(pokemon)){
                //Lo encuentra y elimina.
                this.lista[i]=null;
                this.actual--;
            }
        }
    }

    /**
     * Método para obtener un elemento dada su posición.
     * @param posicion a buscar.
     * @return el elemento.
     */
    public Pokemon obtener(int posicion){
        //Valida la posición.
        try {
            Utils.validarNumeroEnRango(posicion,0,this.actual);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }

        //Recorre la lista.
        for(int i=0;i<this.actual;i++){
            //Si i llegó a la posición indicada.
            if(i==posicion){
                //i corresponde a la posición dada y arroja el elemento.
                return this.lista[i];
            }
        }

        //No se encontró la posición.
        return null;
    }
}