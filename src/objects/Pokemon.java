package objects;

import list.Elemento;
import system.Utils;

/**
 * clase Pokemón
 */
public class Pokemon implements Elemento{
    /**
     * numero de identificacion del pokemón
     */
    private int id;
    /**
     * Nombre del pokemón
     */
    private String nombre;
    /**
     * etapa actual del pokemón
     */
    private String etapa;
    /**
     * La siguiente evolucion del pokemón
     */
    private String evolucionSiguiente;
    /**
     * la evolucion previa del pokemón
     */
    private String evolucionPrevia;
    /**
     * el primer tipo del pokemón
     */
    private String tipo1;
    /**
     * el segundo tipo del pokemón
     */
    private String tipo2;

    /**
     * CONSTRUCTOR
     * @param id identificacion del pokemón
     * @param nombre nombre del pokemon
     * @param etapa etapa actual del pokemon
     * @param evolucionSiguiente evolucion siguiente del pokemon
     * @param evolucionPrevia evolucion previa del pokemon
     * @param tipo1 primer tipo del pokemon
     * @param tipo2 segundo tipo del pokemon
     */
    public Pokemon(int id, String nombre, String etapa, String evolucionSiguiente, String evolucionPrevia, String tipo1, String tipo2) {
        //Valida la ID.
        try{
            Utils.validarNumero(id,1,151);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }
        this.id = id;

        //Valida el nombre.
        try{
            Utils.validarString(nombre);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }
        this.nombre = nombre;

        //Valida la etapa.
        try{
            Utils.validarString(etapa);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }
        this.etapa = etapa;

        this.evolucionSiguiente = evolucionSiguiente;
        this.evolucionPrevia = evolucionPrevia;

        //Valida el primer tipo.
        try{
            Utils.validarString(tipo1);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }
        this.tipo1 = tipo1;

        //Valida el segundo tipo.
        try{
            Utils.validarString(tipo2);
        }catch (IllegalArgumentException ex){
            System.out.println("Ha ocurrido un error: "+ex);
        }
        this.tipo2 = tipo2;
    }

    //Los Getter's.

    /**
     * metodo que retorna la id del pokemon
     * @return id del pokemon
     */
    public int getId() {
        return id;
    }

    /**
     * metodo que retorna e lnombre del pokemon
     * @return nombre del pokemon
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * metodo que retorna la etapa actual del pokemon
     * @return etapa del pokemon
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * metodo que retorna la evolucion siguiente del pokemon
     * @return evolucion siguiente
     */
    public String getEvolucionSiguiente() {
        return evolucionSiguiente;
    }

    /**
     * metodo que retorna la evolucion previa del pokemon
     * @return evolucion previa
     */
    public String getEvolucionPrevia() {
        return evolucionPrevia;
    }

    /**
     * metodo que retorna el primer tipo del pokemon
     * @return primer tipo del pokemon
     */
    public String getTipo1() {
        return tipo1;
    }

    /**
     * metodo que retorna el segundo tipo del pokemon
     * @return segundo tipo del pokemon
     */
    public String getTipo2() {
        return tipo2;
    }

    /**
     * Método que compara el elemento con otro.
     * @param elemento a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean esIgual(Elemento elemento) {

        //si el elemento ingresado es igual al que hay, retorna true
        if(this == elemento){
            return true;
        }

        //si el elemento ingresado es instanciado como pokemón
        if(elemento instanceof Pokemon pokemon){
            return this.id == pokemon.id;
        }
        return false;
    }

    /**
     * Método que compara un elemento con otro.
     * @param elemento a comparar.
     * @return 0 si son el mismo, -1 si es menor y 1 si es mayor.
     */
    @Override
    public int compararCon(Elemento elemento) {

        //Comparación consigo mismo.
        if(this==elemento){
            return 0;
        }

        //Comparación por nombre.
        if(elemento instanceof Pokemon pokemon){
            return this.nombre.compareTo(pokemon.nombre);
        }

        //No se puede comparar.
        throw new IllegalArgumentException("El elemento no es un Pokémon.");
    }

    /**
     * Método que convierte un Pokémon a Integer.
     * @return el ID del Pokémon.
     */
    public int toInt(){return this.id;}

    /**
     * Método que convierte el Pokémon en un String.
     * @return el nombre del Pokémon.
     */
    public String toString(){return this.nombre;}

    /**
     * Método que verifica si el Pokémon tiene o no una evolución.
     * @return true si tiene evolución, false en caso contrario.
     */
    public boolean tieneEvo(){return this.getEvolucionSiguiente()!=null;}

    /**
     * Método que verifica si el Pokémon evolucionó de otro o no.
     * @return true si es una evolución, false en caso contrario.
     */
    public boolean esEvo(){return this.getEvolucionPrevia()!=null;}
}