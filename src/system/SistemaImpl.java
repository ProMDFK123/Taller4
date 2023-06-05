package system;

import list.Elemento;
import list.ListaNodoDoble;
import list.NodoDoble;
import objects.Pokemon;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * clase que implementa el sistema
 */
public class SistemaImpl implements Sistema{

    /**
     * la lista de los nodos
     */
    private final ListaNodoDoble pokedex;

    /**
     * CONSTRUCTOR
     */
    public SistemaImpl(){
        this.pokedex = new ListaNodoDoble();
        this.menu();
    }

    /**
     * metodo que contiene el menu
     */
    public void menu(){

        //para inicializar la variable
        int opcionInt = -2;

        while(true) {
            StdOut.println("\n ~ ~ ~ ~ ~ POKEDEX ~ ~ ~ ~ ~ ");
            StdOut.println("""
                                    
                    ¿Qué desea hacer?
                                    
                    [1] Desplegar pokemones dado un rango y su ID
                    [2] Desplegar los pokemones alfabeticamente
                    [3] Desplegar pokemones por tipo
                    [4] Desplegar pokemomes de primera evolución
                    [5] Busqueda personalizada
                    [6] Cerrar la pokedex
                    """);
            StdOut.print("ingrese su opción: ");

            //validacion de opcion de entrada
            try {
                String opcion = StdIn.readString();
                opcionInt = Integer.parseInt(opcion);

            }catch (Exception e){
                StdOut.println("Ingrese un dato válido");
                continue;
            }

            //si la opcion es distinta de -2 acceso a las opciones
            if(opcionInt != -2) {

                switch (opcionInt) {

                    case 1: //Desplegar pokemones dado un rango y su ID

                        StdOut.println("Desplegar pokemones dado un rango y su ID: \n");

                        int minimo = 0;
                        int maximo = 0;

                        while(true) {
                            try {
                                StdOut.println("Ingrese el número Mínimo de despliegue (minimo 0): ");
                                String minimoString = StdIn.readString();
                                minimo = Integer.parseInt(minimoString);
                            } catch (Exception e) {
                                print("Ingrese un dato válido");
                                continue;
                            }
                            if(minimo<0 || minimo>151){
                                print("Ingrese un rango válido (desde 0 a 151)");
                                continue;
                            }
                            break;
                        }

                        while(true) {
                            try {
                                StdOut.println("Ingrese el número Máximo de despliegue: ");
                                String maximoString = StdIn.readString();
                                maximo = Integer.parseInt(maximoString);
                            } catch (Exception e) {
                                print("Ingrese un dato válido");
                                continue;
                            }
                            if(maximo<0 || maximo>151){
                                print("Ingrese un rango válido (desde 0 a 151)");
                                continue;
                            }
                            break;
                        }
                        desplegarPokemon(minimo,maximo);

                        break;

                    case 2: //Desplegar los pokemones alfabeticamente
                        print("Desplegar pokemones alfabéticamente: \n");
                        desplegarAlfabetico();

                        break;

                    case 3: //Desplegar pokemones por tipo
                        while(true) {
                            print("Desplegar pokemón po su tipo: \n");
                            print("Ingrese el tipo del pokemón a buscar: ");
                            String tipo = StdIn.readString();

                            if (validarTipoPokemon(tipo)) {
                                desplegarTipo(tipo);
                                break;
                            } else {
                                print("Ingrese un tipo de pokemón válido.");
                            }
                        }
                        break;

                    case 4: //Desplegar pokemomes de primera evolución

                        break;

                    case 5: //Busqueda personalizada

                        break;

                    case 6: //Cerrar la pokedex
                        StdOut.println("Cerrando pokedex...");
                        break;

                    default:
                        StdOut.println("Ingrese una opción váida");
                }
            }

            //si se escogio la opcion 6, se cierra el programa
            if(opcionInt==6){
                break;
            }

        }

    }

    //TODO TERMINAR
    /**
     * metodo que lee el archivo KANTO
     */
    public void leerArchivo(){

        //validar existencia del archivo
        ArchivoEntrada archivoEntrada;
        try{
            archivoEntrada = new ArchivoEntrada("Kanto.txt");

        }catch (Exception e){
            StdOut.println("El archivo no existe");
            return;
        }

        //si el archivo existe, leerlo

        while (archivoEntrada.isEndFile()){

            //verificar si el pokemón tiene campos validos
            try{

                Registro registro = archivoEntrada.getRegistro();

                //DATOS DEL POKEMON : ID,Nombre,Etapa,Evolución Siguiente,Evolución Previa,Tipo 1,Tipo 2


                //el .strip() elimina los espacios delante y detras de un registro
                String id = registro.getString().strip();

                //TODO HACER VALIDACION DE SALTOS DE LINEAS EN EL ARCHIVO
                //supuesta validacio nde saltos de linea, comprobarlo
                if(id.equalsIgnoreCase("")){
                    break;
                }

                String nombre = registro.getString().strip();
                String etapa = registro.getString().strip();
                String evolucionSiguiente = registro.getString().strip();
                String evolucionPrevia = registro.getString().strip();
                String tipo1 = registro.getString().strip();
                String tipo2 = registro.getString().strip();


                //VALIDACIONES DE DATOS DE ENTRADA

                //validar que el id sean numero y que estén dentro del rango
                int idInt = Integer.parseInt(id);
                if(idInt < 1 || idInt > 151){
                    throw new Exception  ("El pokemón tiene un id inválido");
                }

                //validar la etapa del pokemon
                if(!etapa.equalsIgnoreCase("basico") && !etapa.equalsIgnoreCase("primera evolucion") && !etapa.equalsIgnoreCase("segunda evolucion") ){
                    throw  new Exception("El pokemón tiene una etapa inválida");
                }


                //validar primer y segundo tipo del pokemon
                if(tipo1.length()==0){
                    throw new Exception("El pokemón debe tener almenos 1 tipo.");

                }else if(!validarTipoPokemon(tipo1)){
                    throw new Exception("El tipo del pokemon no existe");

                    //si el tipo 1 existe, pregunto si tiene un segundo
                } else if (validarTipoPokemon(tipo1)) {

                    //si el segundo tipo no existe, se arroja error
                    if(!validarTipoPokemon(tipo2) || !tipo2.equalsIgnoreCase("")){
                        throw new Exception("El segundo tipo del pokemón no existe");
                    }
                }

                //si las validaciones están correctas, se crea el pokemón
                Pokemon pokemon = new Pokemon(idInt,nombre,etapa,evolucionSiguiente,evolucionPrevia,tipo1,tipo2);
                pokedex.agregar(pokemon);


                //error de pokemon con campos invalidos
            }catch(Exception e){
                StdOut.println(" |!| Pokemón con campos inválidos |!| ");
            }
        }


    }

    /**
     * Método que despliega una lista de Pokémon dado un rango.
     * @param Inicio - valor inicial del rango.
     * @param Fin - valor final del rango.
     */
    @Override
    public void desplegarPokemon(int Inicio, int Fin) {
        //Variables y listas auxiliares del método.
        List<Pokemon> pokeList = new ArrayList<>();
        List<Integer> listaAux = new ArrayList<>();
        StringBuilder pokemonInfo = new StringBuilder();

        //Recorre la lista general.
        for(NodoDoble aux=this.pokedex.getHead(); aux!=null; aux=aux.getNext()){
            //Elemento es instancia de Pokemon.
            if(aux.getElemento() instanceof Pokemon pokemon){
                //Guarda el ID del Pokémon en una lista de ID auxiliar.
                int auxID = pokemon.toInt();
                listaAux.add(auxID);
            }
        }

        //Ordena la lista auxiliar de ID.
        Collections.sort(listaAux);

        //Recorre la lista auxiliar de ID.
        for(int i:listaAux){
            //Recorre la lista general.
            for(NodoDoble aux=this.pokedex.getHead(); aux!=null; aux=aux.getNext()){
                //Guarda el elemento.
                Elemento elementoAux = aux.getElemento();
                Pokemon pokeAux = (Pokemon) elementoAux;

                //Compara si el ID del Pokémon auxiliar es idéntico a un elemento en la lista auxiliar de ID.
                if(pokeAux.getId()==i){
                    //Guarda la lista en la lista final.
                    pokeList.add(pokeAux);
                }
            }
        }

        //Recorre la lista de Pokémon ordenados.
        for(Pokemon auxPoke : pokeList){
            //Saca la información de cada Pokémon.
            pokemonInfo.append("ID: ").append(auxPoke.getId()).append("\n");
            pokemonInfo.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            pokemonInfo.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if(!auxPoke.tieneEvo()){
                pokemonInfo.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if(!auxPoke.esEvo()){
                pokemonInfo.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if(auxPoke.getTipo1().equals(auxPoke.getTipo2())){
                pokemonInfo.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            }else {
                pokemonInfo.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            pokemonInfo.append("=========================================================\n");
        }

        String info = pokemonInfo.toString();
        print(info);
    }

    @Override
    public void desplegarAlfabetico() {
        List<String> alphaName = new ArrayList<>();
        List<Pokemon> pokemonList = new ArrayList<>();
        StringBuilder pokemonInfo = new StringBuilder();

        for(NodoDoble aux=this.pokedex.getHead(); aux!=null; aux=aux.getNext()){
            String auxName = aux.getElemento().toString();
            alphaName.add(auxName);
        }

        Collections.sort(alphaName);

        for(String name : alphaName){
            for(NodoDoble aux=this.pokedex.getHead(); aux!=null; aux=aux.getNext()){
                Pokemon pkm = (Pokemon) aux.getElemento();

                if(pkm.getNombre().equals(name)){
                    pokemonList.add(pkm);
                }
            }
        }

        //Recorre la lista ordenada.
        for(Pokemon auxPoke : pokemonList){
            pokemonInfo.append("ID: ").append(auxPoke.getId()).append("\n");
            pokemonInfo.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            pokemonInfo.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if(!auxPoke.tieneEvo()){
                pokemonInfo.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if(!auxPoke.esEvo()){
                pokemonInfo.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if(auxPoke.getTipo1().equals(auxPoke.getTipo2())){
                pokemonInfo.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            }else {
                pokemonInfo.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            pokemonInfo.append("=========================================================\n");
        }

        String info = pokemonInfo.toString();

        print(info);
    }

    @Override
    public void desplegarTipo(String tipo) {
        List<Pokemon> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(NodoDoble aux=this.pokedex.getHead(); aux!=null; aux=aux.getNext()){
            Pokemon pokemon = (Pokemon) aux.getElemento();
            String tipo1 = pokemon.getTipo1();
            String tipo2 = pokemon.getTipo2();

            if(tipo.equals(tipo1) || tipo.equals(tipo2)){
                lista.add(pokemon);
            }
        }

        //Recorre la lista ordenada.
        for(Pokemon auxPoke : lista){
            sb.append("ID: ").append(auxPoke.getId()).append("\n");
            sb.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            sb.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if(!auxPoke.tieneEvo()){
                sb.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if(!auxPoke.esEvo()){
                sb.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if(auxPoke.getTipo1().equals(auxPoke.getTipo2())){
                sb.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            }else {
                sb.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            sb.append("=========================================================\n");
        }

        String info = sb.toString();

        print(info);
    }

    @Override
    public void desplegarPrimeraEvolucion() {
        StringBuilder sb = new StringBuilder();
        List<Pokemon> primeraEvo = new ArrayList<>();
        List<Integer> id = new ArrayList<>();

        //Recorre la lista para separar los Pokémon que son primera evolución.
        for(NodoDoble aux=this.pokedex.getHead(); aux!=null; aux=aux.getNext()){
            Pokemon pkmaux = (Pokemon) aux.getElemento();

            //Guarda el ID de un Pokémon de primera evolución en una lista auxiliar.
            if(pkmaux.getEtapa().equals("Primera Evolucion")){
                int auxID = pkmaux.toInt();
                id.add(auxID);
            }
        }

        //Ordena la lista auxiliar de ID de manera decreciente (de mayor a menor).
        Comparator<Integer> comparator = Collections.reverseOrder();
        id.sort(comparator);

        //Recorre la lista auxiliar.
        for(int i : id) {
            //Recorre la lista general.
            for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
                Pokemon pokemon = (Pokemon) aux.getElemento();

                //Guarda el Pokémon en una lista especializada.
                if(i==pokemon.getId()){
                    primeraEvo.add(pokemon);
                }
            }
        }

        //Recorre la lista ordenada.
        for(Pokemon auxPoke : primeraEvo){
            sb.append("ID: ").append(auxPoke.getId()).append("\n");
            sb.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            sb.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if(!auxPoke.tieneEvo()){
                sb.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if(!auxPoke.esEvo()){
                sb.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if(auxPoke.getTipo1().equals(auxPoke.getTipo2())){
                sb.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            }else {
                sb.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            sb.append("=========================================================\n");
        }

        String info = sb.toString();

        print(info);
    }

    @Override
    public void busquedaPersonalizada(String nombre, boolean estado) {
        //Valida el nombre ingresado.
        try {
            Utils.validarString(nombre);
        }catch (IllegalArgumentException ex){
            print("Ha ocurrido un error: "+ex);
        }

        StringBuilder sb = new StringBuilder();

        //Recorre la lista en busca del Pokémon.
        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            Pokemon pokemonAux = (Pokemon) aux.getElemento();

            if(nombre.equals(pokemonAux.getNombre())){
                sb.append("ID: ").append(pokemonAux.getId()).append("\n");
                sb.append("Nombre: ").append(pokemonAux.getNombre()).append("\n");
                sb.append("Etapa: ").append(pokemonAux.getEtapa()).append("\n");
                if(!pokemonAux.tieneEvo()){
                    sb.append("Evolución Siguiente: ").append(pokemonAux.getEvolucionSiguiente()).append("\n");
                }
                if(!pokemonAux.esEvo()){
                    sb.append("Evolución Previa: ").append(pokemonAux.getEvolucionPrevia()).append("\n");
                }
                if(pokemonAux.getTipo1().equals(pokemonAux.getTipo2())){
                    sb.append("Tipo: ").append(pokemonAux.getTipo1()).append("\n");
                }else {
                    sb.append("Tipos: ").append(pokemonAux.getTipo1()).append(", ").append(pokemonAux.getTipo2()).append("\n");
                }
                sb.append("=========================================================\n");
            }

            subMenu();

            String opcion = StdIn.readString();

            switch (opcion){
                case "1" -> desplegarEvolucion(pokemonAux);
                case "2" -> estado = salir(estado);
            }
        }
    }

    @Override
    public void busquedaPersonalizada(int id, boolean estado) {
        //Valida el nombre ingresado.
        try {
            Utils.validarNumero(id,1,151);
        }catch (IllegalArgumentException ex){
            print("Ha ocurrido un error: "+ex);
        }

        StringBuilder sb = new StringBuilder();

        //Recorre la lista en busca del Pokémon.
        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            Pokemon pokemonAux = (Pokemon) aux.getElemento();

            if(id== pokemonAux.getId()){
                sb.append("ID: ").append(pokemonAux.getId()).append("\n");
                sb.append("Nombre: ").append(pokemonAux.getNombre()).append("\n");
                sb.append("Etapa: ").append(pokemonAux.getEtapa()).append("\n");
                if(!pokemonAux.tieneEvo()){
                    sb.append("Evolución Siguiente: ").append(pokemonAux.getEvolucionSiguiente()).append("\n");
                }
                if(!pokemonAux.esEvo()){
                    sb.append("Evolución Previa: ").append(pokemonAux.getEvolucionPrevia()).append("\n");
                }
                if(pokemonAux.getTipo1().equals(pokemonAux.getTipo2())){
                    sb.append("Tipo: ").append(pokemonAux.getTipo1()).append("\n");
                }else {
                    sb.append("Tipos: ").append(pokemonAux.getTipo1()).append(", ").append(pokemonAux.getTipo2()).append("\n");
                }
                sb.append("=========================================================\n");
            }

            subMenu();

            String opcion = StdIn.readString();

            switch (opcion){
                case "1" -> desplegarEvolucion(pokemonAux);
                case "2" -> estado = salir(estado);
            }
        }
    }

    /**
     * Menú de busqueda personalizada
     */
    public void subMenu(){
        String menu = """
                ¿Qué desea hacer?
                
                [1] Ver evoluciones.
                [2] Salir.
                """;

        print(menu);
    }

    /**
     * Método para mirar las evoluciones de un Pokémon.
     * @param pokemon con evoluciones.
     */
    @Override
    public void desplegarEvolucion(Pokemon pokemon) {
        print(pokemon.getEvolucionSiguiente());
    }

    /**
     * Método para finalizar el programa.
     * @param estado del programa.
     * @return false para salir.
     */
    @Override
    public boolean salir(boolean estado) {

        return !estado;
    }

    /**
     * Método auxiliar para imprimir.
     * @param string a imprimir.
     */
    public void print(String string){
        System.out.println(string);
    }

    /**
     * metodo que valida el tipo de un pokemon
     * @param tipoIngresado el tipo que se ingresa
     * @return true si existe, false si no
     */
    public boolean validarTipoPokemon(String tipoIngresado){

        //todos los tipos de pokemon que existen.
        String tiposDePokemon[] = {"normal","Bicho", "Dragón", "Eléctrico", "Hada", "Lucha", "Fuego", "Volador", "Fantasma", "Planta", "Tierra", "Hielo", "Normal", "Veneno", "Psíquico", "Roca", "Acero y Agua"};

        //preguntar si el tipo ingresado es alguno de los 19 que existen
        for (int i = 0; i <tiposDePokemon.length ; i++) {

            if(!tipoIngresado.equalsIgnoreCase(tiposDePokemon[i])){
                return false;
            }
        }

        return true;
    }
}
