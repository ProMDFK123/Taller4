package system;

import list.Elemento;

/**
 * Clase que contiene métodos auxiliares
 */
public class Utils {
    //Constructor privado y vacío.
    private Utils(){}

    /**
     * Método que valida un atributo de tipo String.
     * @param string a validar.
     */
    public static void validarString(String string){
        if(string==null || string.length()==0){
            throw new IllegalArgumentException("El string es inválido, intente con otro.");
        }
    }

    /**
     * Método que valida un elemento.
     * @param elemento
     */
    public static void validarElemento(Elemento elemento){
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
        if(integer<min || integer>max){
            throw new IllegalArgumentException("El número ingresado no se encuentra dentro del rango.");
        }
    }
}
