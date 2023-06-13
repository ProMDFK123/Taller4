package system;

import list.Elemento;

/**
 * Clase que contiene métodos auxiliares
 */
public class Utils {

    /**
     * CONSTRUCTOR (privado y vacío)
     */
    private Utils(){}

    /**
     * Método que valida un atributo de tipo String.
     * @param string a validar.
     */
    public static void validarString(String string){

        //si el string es nulo o no tiene carácteres se arroja error
        if(string==null || string.length()==0){
            throw new IllegalArgumentException("El string es inválido, intente con otro.");
        }
    }

    /**
     * Método que valida un elemento.
     * @param elemento elemento ingresado
     */
    public static void validarElemento(Elemento elemento){

        //si el elemento es nulo se arroja mensaje
        if(elemento==null){
            throw new IllegalArgumentException("El elemento es nulo.");
        }
    }

    /**
     * Método que valida si un número se encuentra dentro de un determinado rango.
     * @param integer - número a validar.
     * @param min - valor mínimo del rango.
     * @param max - valor máximo del rango.
     */
    public static void validarNumero(int integer, int min, int max){

        //si el numero que se pide es menor o mayor al rango, se arroja error
        if(integer<min || integer>max){
            throw new IllegalArgumentException("El número ingresado no se encuentra dentro del rango.");
        }
    }

    /**
     * metodo que valida el tipo de un pokemon
     * @param tipoIngresado el tipo que se ingresa
     * @return true si existe, false si no
     */
    public static boolean validarTipoPokemon(String tipoIngresado){

        //todos los tipos de pokemon que existen.
        String tiposDePokemon[] = {"Normal","Lucha","Volador","Veneno","Tierra","Roca","Insecto","Fantasma","Acero","Fuego","Agua","Hierba","Electrico","Psiquico","Hielo","Dragon","Siniestro","Hada"};

        //preguntar si el tipo ingresado es alguno de los 19 que existen
        for (int i = 0; i <tiposDePokemon.length ; i++) {

            if(tipoIngresado.equalsIgnoreCase(tiposDePokemon[i])){
                return true;
            }
        }

        return false;
    }

    /**
     * Método que valida la etapa de un Pokémon.
     * @param etapa
     * @return
     */
    public static boolean validarEtapa(String etapa){
        //Revisa si la etapa es básico, primera evolución o segunda evolución.
        if(etapa.equalsIgnoreCase("Basico") || etapa.equals("Primera Evolucion") || etapa.equalsIgnoreCase("Segunda Evolucion")){
            //Es básico, primera o segunda evolución.
            return true;
        }

        //No es una opción válida.
        return false;
    }
}
