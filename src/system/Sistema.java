package system;

import objects.Pokemon;

/**
 * Interface con los contratos.
 */
public interface Sistema {
    /**
     * Método que despliega una lista de Pokémon dado un rango.
     * @param idInicio - valor inicial del rango.
     * @param idFin - valor final del rango.
     */
    void desplegarPokemon(int idInicio, int idFin);

    /**
     * Método que despliega todos los Pokémon almacenados.
     */
    void desplegarAlfabetico();

    /**
     * Método que despliega todos los Pokémon dado un tipo.
     * @param tipo a buscar.
     */
    void desplegarTipo(String tipo);

    /**
     * Desplegar todos los pokémon que sean primera evolución.
     */
    void desplegarPrimeraEvolucion();

    /**
     * Buscar un Pokémon por su nombre.
     * @param nombre del Pokémon.
     */
    void busquedaPersonalizada(String nombre);

    /**
     * Buscar un Pokémon por su ID.
     * @param id del Pokémon.
     */
    void busquedaPersonalizada(int id);

    /**
     * Método que deja desplazar entre las evoluciónes del Pókemon.
     * @param pokemon con evoluciones.
     */
    void desplegarEvolucion(Pokemon pokemon);

    /**
     * Salir al menú principal.
     * @param estado del programa.
     * @return el contrario de estado.
     */
    boolean salir(boolean estado);
}
