package objects;

import list.Elemento;
import system.Utils;

public class Pokemon implements Elemento{
    private int id;
    private String nombre;
    private String etapa;
    private String evolucionSiguiente;
    private String evolucionPrevia;
    private String tipo1;
    private String tipo2;

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
     * @return la ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return el nombre del Pokémon.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return la etapa en que se encuentra el Pokémon.
     */
    public String getEtapa() {
        return etapa;
    }

    /**
     * @return las posibles evoluciones.
     */
    public String getEvolucionSiguiente() {
        return evolucionSiguiente;
    }

    /**
     * @return la pre-evolución del Pokémon.
     */
    public String getEvolucionPrevia() {
        return evolucionPrevia;
    }

    /**
     * @return el primer tipo del Pokémon.
     */
    public String getTipo1() {
        return tipo1;
    }

    /**
     * @return el segundo tipo del Pokémon.
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
        if(this==elemento){
            return true;
        }

        if(elemento instanceof Pokemon pokemon){
            return this.id== pokemon.id;
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
