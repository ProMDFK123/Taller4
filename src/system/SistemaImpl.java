package system;

import list.Elemento;
import list.ListaNodoDoble;
import list.NodoDoble;
import objects.Pokemon;
import ucn.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * clase que implementa el sistema
 */
public class SistemaImpl implements Sistema {

    /**
     * la lista de los nodos
     */
    private final ListaNodoDoble pokedex;

    /**
     * CONSTRUCTOR
     */
    public SistemaImpl() throws IOException {

        this.pokedex = new ListaNodoDoble();
        leerArchivo();
        this.leerArchivo();
        this.menu();
    }

    /**
     * metodo que contiene el menu
     */
    public void menu() {

        //para inicializar la variable
        int opcionInt = -2;

        while (true) {
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

            } catch (Exception e) {
                StdOut.println("Ingrese un dato válido");
                continue;
            }

            //si la opcion es distinta de -2 acceso a las opciones
            if (opcionInt != -2) {

                switch (opcionInt) {

                    case 1: //Desplegar pokemones dado un rango y su ID

                        StdOut.println("\n ..::.. Desplegar pokemones dado un rango y su ID ..::..  \n");

                        int minimo = -1;
                        int maximo = -1;

                        //los ciclos e if están hechos para poder navegar entre las opciones
                        while(minimo!=0) {

                            //validacion número mínimo
                            while (true) {
                                try {
                                    StdOut.println("Ingrese el número Mínimo de despliegue ((desde 1 a 151) ,0 para volver): ");
                                    String minimoString = StdIn.readString();
                                    minimo = Integer.parseInt(minimoString);
                                } catch (Exception e) {
                                    print("Ingrese un dato válido");
                                    continue;
                                }
                                if (minimo == 0) {
                                    break;
                                } else if (minimo < 0 || minimo > 151) {
                                    print("Ingrese un rango válido (desde 1 a 151)");
                                    continue;
                                }
                                break;
                            }

                            if(minimo!=0) {

                                //validacion número máximo
                                while (true) {
                                    try {
                                        StdOut.println("Ingrese el número Máximo de despliegue (0 para volver): ");
                                        String maximoString = StdIn.readString();
                                        maximo = Integer.parseInt(maximoString);
                                    } catch (Exception e) {
                                        print("Ingrese un dato válido");
                                        continue;
                                    }
                                    if (maximo == 0) {
                                        break;
                                    } else if (maximo < 0 || maximo > 151) {
                                        print("Ingrese un rango válido (desde 1 a 151)");
                                        continue;
                                    } else if (minimo > maximo) {
                                        print("El rango máximo no puede ser menor al mínimo. ");
                                        continue;
                                    }
                                    break;
                                }
                            }
                            if(minimo!=0 || maximo!=0 ) {
                                if(minimo<maximo) {
                                    print("Estos son los pokemones que hay entre " + minimo + " y " + maximo + ": \n");

                                    desplegarPokemon(minimo, maximo);
                                    break;
                                }
                            }
                        }

                        break;

                    case 2: //Desplegar los pokemones alfabeticamente
                        print("\n ..::.. Desplegar pokemones alfabéticamente ..::.. \n");
                        desplegarAlfabetico();

                        break;

                    case 3: //Desplegar pokemones por tipo

                        print("\n ..::.. Desplegar pokemón por su tipo ..::.. \n");
                        while (true) {
                            print("Ingrese el tipo del pokemón a buscar: (0 para volver)");
                            String tipo = StdIn.readString();


                            if (tipo.equalsIgnoreCase("0")) {
                                break;
                            } else if (Utils.validarTipoPokemon(tipo)) {

                                print("Éstos son los pokemónes de tipo " + tipo + ": ");

                                if (Utils.validarTipoPokemon(tipo)) {

                                    desplegarTipo(tipo);
                                    break;
                                }
                            }else{
                                print("Ingrese un tipo de pokemón válido. ");
                            }

                        }
                        break;

                    case 4: //Desplegar pokemomes de primera evolución
                        print("\n ..::.. Desplegar pokemones que están en primera evolución ..::.. \n");
                        desplegarPrimeraEvolucion();
                        break;

                    case 5: //Busqueda personalizada
                        print("\n ..::.. Búsqueda personalizada ..::.. \n");

                        int opcion = 0;
                        int terminar = 0;

                        while(terminar!=-1) {

                            //validacion de datos
                            while (true) {
                                try {
                                    StdOut.println("********************\n|1| Buscar por ID \n|2| Buscar por Nombre \n|3| Volver \nSu opcion: ");
                                    String opcionString = StdIn.readString();
                                    opcion = Integer.parseInt(opcionString);

                                } catch (Exception e) {
                                    print("Ingrese un número válido");
                                    continue;
                                }
                                break;
                            }

                            //opciones
                            switch (opcion) {

                                case 1:
                                    int idInt = 0;
                                    print("\n|Búsqueda personalizada|\n ..::.. Buscando por ID ..::.. ");
                                    while(true) {
                                        try {
                                            print("\nIngrese el id del pokemón a buscar (0 para volver) ");
                                            String idString = StdIn.readString();
                                            idInt = Integer.parseInt(idString);
                                        }catch (Exception e){
                                            print("El id es de formato numérico");
                                        }
                                        break;
                                    }
                                    if(idInt==0){
                                        break;
                                    }
                                    print("POKEMON CON ID |" + idInt + "|");
                                    busquedaPersonalizada(idInt,false);
                                    break;

                                case 2:
                                    print("\n|Búsqueda personalizada|\n ..::.. Buscando por NOMBRE ..::.. ");
                                   print("\nIngrese el nombre del pokemón a buscar (0 para volver) ");
                                   String nombrePokemon = StdIn.readString();

                                   if(nombrePokemon.equalsIgnoreCase("0")){
                                       break;
                                   }
                                    print("POKEMON CON NOMBRE |" + nombrePokemon + "|");
                                    busquedaPersonalizada(nombrePokemon,false);

                                    break;

                                case 3:
                                    terminar = -1;
                                    print("Volviendo...");
                                    break;

                                default:
                                    print("Opción inválida");

                            }

                        }
                        break;

                    case 6: //Cerrar la pokedex

                        StdOut.println("Cerrando pokedex...");
                        break;

                    default:
                        StdOut.println("Ingrese una opción váida");
                }

                //si se escogio la opcion 6, se cierra el programa

            }
            if (opcionInt == 6) {
                break;
            }
        }
    }

    /**
     * metodo que lee el archivo KANTO
     */
    public void leerArchivo() throws IOException {

        //validar existencia del archivo
        ArchivoEntrada archivoEntrada;

        try {
            archivoEntrada = new ArchivoEntrada("Kanto.txt");

        } catch (Exception e) {
            StdOut.println("El archivo no existe");
            return;
        }

        while (!archivoEntrada.isEndFile()) {

            //verificar si el pokemón tiene campos validos
            try {

                Registro registro = archivoEntrada.getRegistro();

                String id = registro.getString().strip();
                String nombre = registro.getString().strip();
                String etapa = registro.getString().strip();
                String evolucionSiguiente = registro.getString().strip();
                String evolucionPrevia = registro.getString().strip();
                String tipo1 = registro.getString().strip();
                String tipo2 = registro.getString().strip();

                //VALIDACIONES DE DATOS DE ENTRADA

                //validar que el id sea un numero y que estén dentro del rango
                int idInt = Integer.parseInt(id);
                try {
                    Utils.validarNumero(idInt, 1, 151);
                } catch (IllegalArgumentException ex) {
                    print("Ha ocurrido un error: " + ex);
                }

                //validar la etapa del pokemon
                if (!etapa.equalsIgnoreCase("basico") && !etapa.equalsIgnoreCase("primera evolucion") && !etapa.equalsIgnoreCase("segunda evolucion")) {
                    throw new Exception("El pokemón tiene una etapa inválida");
                }

                //validar primer y segundo tipo del pokemon
                if (tipo1.length() == 0) {
                    throw new Exception("El pokemón debe tener almenos 1 tipo.");

                } else if (!Utils.validarTipoPokemon(tipo1)) {
                    throw new Exception("El tipo del pokemon no existe");

                    //si el tipo 1 existe, pregunto si tiene un segundo
                } else if (Utils.validarTipoPokemon(tipo1)) {

                    //si el segundo tipo no existe, se arroja error
                    if (!Utils.validarTipoPokemon(tipo2) || !tipo2.equalsIgnoreCase("")) {
                        throw new Exception("El segundo tipo del pokemón no existe");
                    }
                }


                if (etapa.equalsIgnoreCase("Basico")) {

                    evolucionSiguiente = registro.getString().strip();
                    evolucionPrevia = null;
                    String aux = registro.getString().strip();

                    //Valida si el campo siguiente es un tipo o no.
                    if (Utils.validarTipoPokemon(aux)) {
                        tipo1 = aux;
                        tipo2 = registro.getString().strip();
                        Pokemon pokemon = new Pokemon(idInt, nombre, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                        pokedex.agregar(pokemon);
                    } else {
                        String aux2 = registro.getString().strip();
                        if (Utils.validarTipoPokemon(aux2)) {
                            tipo1 = aux2;
                            tipo2 = registro.getString().strip();
                            Pokemon pokemon = new Pokemon(idInt, nombre, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                            pokedex.agregar(pokemon);
                        }
                    }
                    //Caso de Pokémon que es primera evolución.
                } else if (etapa.equalsIgnoreCase("Primera Evolucion")) {
                    evolucionSiguiente = registro.getString().strip();
                    String aux = registro.getString().strip();
                    if (!Utils.validarTipoPokemon(aux)) {
                        evolucionPrevia = aux;
                        tipo1 = registro.getString().strip();
                        tipo2 = registro.getString().strip();
                        Pokemon pokemon = new Pokemon(idInt, nombre, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                        pokedex.agregar(pokemon);
                    } else {
                        evolucionPrevia = null;
                        tipo1 = aux;
                        tipo2 = registro.getString().strip();
                        Pokemon pokemon = new Pokemon(idInt, nombre, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                        pokedex.agregar(pokemon);
                    }
                    //Caso en que el Pokémon es segundaevolución.
                } else if (etapa.equalsIgnoreCase("Segunda Evolucion")) {


                    evolucionSiguiente = null;
                    evolucionPrevia = registro.getString().strip();
                    String aux = registro.getString().strip();

                    if (Utils.validarTipoPokemon(aux)) {
                        tipo1 = aux;
                        tipo2 = registro.getString().strip();
                        Pokemon pokemon = new Pokemon(idInt, nombre, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                        pokedex.agregar(pokemon);
                    } else {
                        String aux2 = registro.getString().strip();
                        if (Utils.validarTipoPokemon(aux2)) {
                            tipo1 = aux2;
                            tipo2 = registro.getString().strip();
                            Pokemon pokemon = new Pokemon(idInt, nombre, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                            pokedex.agregar(pokemon);
                        }
                    }
                }

                //error de pokemon con campos invalidos
            } catch (Exception e) {
                StdOut.println(" |!| Pokemón con campos inválidos |!| ");
            }
        }
    }

    /**
     * Método que despliega una lista de Pokémon dado un rango.
     *
     * @param Inicio - valor inicial del rango.
     * @param Fin    - valor final del rango.
     */
    @Override
    public void desplegarPokemon(int Inicio, int Fin) {

        //Variables y listas auxiliares del método.
        List<Pokemon> pokeList = new ArrayList<>();
        List<Integer> listaAux = new ArrayList<>();
        StringBuilder pokemonInfo = new StringBuilder();

        //Recorre la lista general.
        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {

            //Elemento es instancia de Pokemon.
            if (aux.getElemento() instanceof Pokemon pokemon) {

                //Guarda el ID del Pokémon en una lista de ID auxiliar.
                int auxID = pokemon.toInt();
                listaAux.add(auxID);
            }
        }

        //Ordena la lista auxiliar de ID.
        Collections.sort(listaAux);

        //Recorre la lista auxiliar de ID.
        for (int i : listaAux) {
            //Recorre la lista general.
            for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
                //Guarda el elemento.
                Elemento elementoAux = aux.getElemento();
                Pokemon pokeAux = (Pokemon) elementoAux;

                //Compara si el ID del Pokémon auxiliar es idéntico a un elemento en la lista auxiliar de ID.
                if (pokeAux.getId() == i) {
                    //Guarda la lista en la lista final.
                    pokeList.add(pokeAux);
                }
            }
        }

        //Recorre la lista de Pokémon ordenados.
        for (Pokemon auxPoke : pokeList) {

            //Saca la información de cada Pokémon.
            pokemonInfo.append("ID: ").append(auxPoke.getId()).append("\n");
            pokemonInfo.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            pokemonInfo.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");

            if (!auxPoke.tieneEvo()) {
                pokemonInfo.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if (!auxPoke.esEvo()) {
                pokemonInfo.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if (auxPoke.getTipo1().equals(auxPoke.getTipo2())) {
                pokemonInfo.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            } else {
                pokemonInfo.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            pokemonInfo.append("=========================================================\n");
        }

        String info = pokemonInfo.toString();
        print(info);
    }

    /**
     * Metodo que despliega todos los pokemones en orden alfabético
     */
    @Override
    public void desplegarAlfabetico() {
        List<String> alphaName = new ArrayList<>();
        List<Pokemon> pokemonList = new ArrayList<>();
        StringBuilder pokemonInfo = new StringBuilder();

        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            String auxName = aux.getElemento().toString();
            alphaName.add(auxName);
        }

        Collections.sort(alphaName);

        for (String name : alphaName) {
            for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
                Pokemon pkm = (Pokemon) aux.getElemento();

                if (pkm.getNombre().equals(name)) {
                    pokemonList.add(pkm);
                }
            }
        }

        //Recorre la lista ordenada.
        for (Pokemon auxPoke : pokemonList) {
            pokemonInfo.append("ID: ").append(auxPoke.getId()).append("\n");
            pokemonInfo.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            pokemonInfo.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if (!auxPoke.tieneEvo()) {
                pokemonInfo.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if (!auxPoke.esEvo()) {
                pokemonInfo.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if (auxPoke.getTipo1().equals(auxPoke.getTipo2())) {
                pokemonInfo.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            } else {
                pokemonInfo.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            pokemonInfo.append("=========================================================\n");
        }

        String info = pokemonInfo.toString();

        print(info);
    }

    /**
     * Método que despliega un pokemon dado un tipo ingresado
     * @param tipo a buscar.
     */
    @Override
    public void desplegarTipo(String tipo) {

        List<Pokemon> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            Pokemon pokemon = (Pokemon) aux.getElemento();
            String tipo1 = pokemon.getTipo1();
            String tipo2 = pokemon.getTipo2();

            if (tipo.equals(tipo1) || tipo.equals(tipo2)) {
                lista.add(pokemon);
            }
        }

        //Recorre la lista ordenada.
        for (Pokemon auxPoke : lista) {
            sb.append("ID: ").append(auxPoke.getId()).append("\n");
            sb.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            sb.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if (!auxPoke.tieneEvo()) {
                sb.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if (!auxPoke.esEvo()) {
                sb.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if (auxPoke.getTipo1().equals(auxPoke.getTipo2())) {
                sb.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            } else {
                sb.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            sb.append("=========================================================\n");
        }

        String info = sb.toString();

        print(info);
    }

    /**
     * Método que despliega la primera evolicion del pokemón
     */
    @Override
    public void desplegarPrimeraEvolucion() {
        StringBuilder sb = new StringBuilder();
        List<Pokemon> primeraEvo = new ArrayList<>();
        List<Integer> id = new ArrayList<>();

        //Recorre la lista para separar los Pokémon que son primera evolución.
        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            Pokemon pkmaux = (Pokemon) aux.getElemento();

            //Guarda el ID de un Pokémon de primera evolución en una lista auxiliar.
            if (pkmaux.getEtapa().equals("Primera Evolucion")) {
                int auxID = pkmaux.toInt();
                id.add(auxID);
            }
        }

        //Ordena la lista auxiliar de ID de manera decreciente (de mayor a menor).
        Comparator<Integer> comparator = Collections.reverseOrder();
        id.sort(comparator);

        //Recorre la lista auxiliar.
        for (int i : id) {
            //Recorre la lista general.
            for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
                Pokemon pokemon = (Pokemon) aux.getElemento();

                //Guarda el Pokémon en una lista especializada.
                if (i == pokemon.getId()) {
                    primeraEvo.add(pokemon);
                }
            }
        }

        //Recorre la lista ordenada.
        for (Pokemon auxPoke : primeraEvo) {
            sb.append("ID: ").append(auxPoke.getId()).append("\n");
            sb.append("Nombre: ").append(auxPoke.getNombre()).append("\n");
            sb.append("Etapa: ").append(auxPoke.getEtapa()).append("\n");
            if (!auxPoke.tieneEvo()) {
                sb.append("Evolución Siguiente: ").append(auxPoke.getEvolucionSiguiente()).append("\n");
            }
            if (!auxPoke.esEvo()) {
                sb.append("Evolución Previa: ").append(auxPoke.getEvolucionPrevia()).append("\n");
            }
            if (auxPoke.getTipo1().equals(auxPoke.getTipo2())) {
                sb.append("Tipo: ").append(auxPoke.getTipo1()).append("\n");
            } else {
                sb.append("Tipos: ").append(auxPoke.getTipo1()).append(", ").append(auxPoke.getTipo2()).append("\n");
            }
            sb.append("=========================================================\n");
        }

        String info = sb.toString();

        print(info);
    }

    /**
     * Método que busca un pokemón por el nombre ingresado
     * @param nombre del Pokémon.
     * @param estado true para seguir buscado, false para no seguir
     */
    @Override
    public void busquedaPersonalizada(String nombre, boolean estado) {
        //Valida el nombre ingresado.
        try {
            Utils.validarString(nombre);
        } catch (IllegalArgumentException ex) {
            print("Ha ocurrido un error: " + ex);
        }

        StringBuilder sb = new StringBuilder();

        //Recorre la lista en busca del Pokémon.
        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            Pokemon pokemonAux = (Pokemon) aux.getElemento();

            if (nombre.equals(pokemonAux.getNombre())) {
                sb.append("ID: ").append(pokemonAux.getId()).append("\n");
                sb.append("Nombre: ").append(pokemonAux.getNombre()).append("\n");
                sb.append("Etapa: ").append(pokemonAux.getEtapa()).append("\n");
                if (!pokemonAux.tieneEvo()) {
                    sb.append("Evolución Siguiente: ").append(pokemonAux.getEvolucionSiguiente()).append("\n");
                }
                if (!pokemonAux.esEvo()) {
                    sb.append("Evolución Previa: ").append(pokemonAux.getEvolucionPrevia()).append("\n");
                }
                if (pokemonAux.getTipo1().equals(pokemonAux.getTipo2())) {
                    sb.append("Tipo: ").append(pokemonAux.getTipo1()).append("\n");
                } else {
                    sb.append("Tipos: ").append(pokemonAux.getTipo1()).append(", ").append(pokemonAux.getTipo2()).append("\n");
                }
                sb.append("=========================================================\n");
            }

            subMenu();

            String opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> desplegarEvolucion(pokemonAux);
                case "2" -> estado = salir(estado);
            }
        }
    }

    /**
     * Métod oque busca un pokemón por un id ingresado
     * @param id del Pokémon.
     * @param estado true para seguir navegando, false si no
     */
    @Override
    public void busquedaPersonalizada(int id, boolean estado) {
        //Valida el nombre ingresado.
        try {
            Utils.validarNumero(id, 1, 151);
        } catch (IllegalArgumentException ex) {
            print("Ha ocurrido un error: " + ex);
        }

        StringBuilder sb = new StringBuilder();

        //Recorre la lista en busca del Pokémon.
        for (NodoDoble aux = this.pokedex.getHead(); aux != null; aux = aux.getNext()) {
            Pokemon pokemonAux = (Pokemon) aux.getElemento();

            if (id == pokemonAux.getId()) {
                sb.append("ID: ").append(pokemonAux.getId()).append("\n");
                sb.append("Nombre: ").append(pokemonAux.getNombre()).append("\n");
                sb.append("Etapa: ").append(pokemonAux.getEtapa()).append("\n");
                if (!pokemonAux.tieneEvo()) {
                    sb.append("Evolución Siguiente: ").append(pokemonAux.getEvolucionSiguiente()).append("\n");
                }
                if (!pokemonAux.esEvo()) {
                    sb.append("Evolución Previa: ").append(pokemonAux.getEvolucionPrevia()).append("\n");
                }
                if (pokemonAux.getTipo1().equals(pokemonAux.getTipo2())) {
                    sb.append("Tipo: ").append(pokemonAux.getTipo1()).append("\n");
                } else {
                    sb.append("Tipos: ").append(pokemonAux.getTipo1()).append(", ").append(pokemonAux.getTipo2()).append("\n");
                }
                sb.append("=========================================================\n");
            }

            subMenu();

            String opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> desplegarEvolucion(pokemonAux);
                case "2" -> estado = salir(estado);
            }
        }
    }

    /**
     * Menú de busqueda personalizada
     */
    public void subMenu() {
        String menu = """
                ¿Qué desea hacer?
                                
                [1] Ver evoluciones.
                [2] Volver.
                """;

        print(menu);
    }

    /**
     * Método para mirar las evoluciones de un Pokémon.
     *
     * @param pokemon con evoluciones.
     */
    @Override
    public void desplegarEvolucion(Pokemon pokemon) {
        print(pokemon.getEvolucionSiguiente());
    }

    /**
     * Método para finalizar el programa.
     *
     * @param estado del programa.
     * @return false para salir.
     */
    @Override
    public boolean salir(boolean estado) {

        return !estado;
    }

    /**
     * Método auxiliar para imprimir.
     *
     * @param string a imprimir.
     */
    public void print(String string) {
        System.out.println(string);
    }

    public void leerArchivoMejorar(){

        File archivo;

        try{
            archivo = new File("archivo.txt");

        }catch (Exception e){
            print("El archiv no existe");
            return;
        }
        try {
            Scanner scanner = new Scanner(archivo);
            //se indica donde sebe terminar la primera linea
            scanner.useDelimiter("\\n");

            //ciclo de lectura, si no hay mas para leer se termina
            while (scanner.hasNext()) {
                try {
                    //guarda la linea en un string
                    String linea = scanner.next();

                    //le quita los espacios delante y detras de los registros
                    linea = linea.trim();

                    //si hay un espacio lo salta
                    if (!linea.equalsIgnoreCase("")) {

                        String palabra[] = linea.split("\\n");

                        for (String primero : palabra) {

                            String datos[] = primero.strip().split(",");

                            //se recorre todas las lineas del archivo
                            for (int i = 0; i < datos.length; i++) {

                                //se guarda cada registro de un pokemon en la variable datoPokemon
                                String datoPokemon = datos[i].strip();

                                //TODO se debe verificar que el datoPokemon sea el nombre, evolucion, tipo, etc. para poder utilizarlo

                                //validar que el id sea un numero y que estén dentro del rango
                                int idInt = Integer.parseInt(datoPokemon);
                                try {
                                    Utils.validarNumero(idInt, 1, 151);
                                } catch (IllegalArgumentException ex) {
                                    print("Ha ocurrido un error: " + ex);
                                }
                                //ya se leyó el ID ahora hay que leer el siguiente datoPokemón.



                            }
                        }
                    }
                }catch (Exception e){
                    print(" |!| Pokemón con campos inválidos |!| ");
                }
            }

            // Cerrar el scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}

