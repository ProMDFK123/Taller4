package system;

import list.Elemento;
import list.ListaNodoDoble;
import list.NodoDoble;
import objects.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SistemaImpl implements Sistema{
    private final ListaNodoDoble pokedex;
    public SistemaImpl(){
        pokedex=new ListaNodoDoble();
        this.menu();
    }

    public void menu(){

    }

    /**
     * Método que despliega una lista de Pokémon dado un rango de ID.
     * @param idInicio - valor inicial del rango.
     * @param idFin - valor final del rango.
     */
    @Override
    public void desplegarPokemon(int idInicio, int idFin) {
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

        for(Pokemon auxPoke : pokeList){
            pokemonInfo.append("ID: "+auxPoke.getId()+"\n");
            pokemonInfo.append("Nombre: "+auxPoke.getNombre()+"\n");
            pokemonInfo.append("Etapa: "+auxPoke.getEtapa()+"\n");
            if(!auxPoke.getEvolucionSiguiente().equals(null)){
                pokemonInfo.append("Evolución Siguiente: "+auxPoke.getEvolucionSiguiente()+"\n");
            }
            if(!auxPoke.getEvolucionPrevia().equals(null)){
                pokemonInfo.append("Evolución Previa: "+auxPoke.getEvolucionPrevia()+"\n");
            }
            if(auxPoke.getTipo1().equals(auxPoke.getTipo2())){
                pokemonInfo.append("Tipo: "+auxPoke.getTipo1()+"\n");
            }else {
                pokemonInfo.append("Tipos: "+auxPoke.getTipo1()+", "+auxPoke.getTipo2()+"\n");
            }
            pokemonInfo.append("=========================================================\n");
        }

        String info = pokemonInfo.toString();
        print(info);
    }

    @Override
    public void desplegarAlfabetico() {

    }

    @Override
    public void desplegarTipo(String tipo) {

    }

    @Override
    public void desplegarPrimeraEvolucion() {

    }

    @Override
    public void busquedaPersonalizada(String nombre) {

    }

    @Override
    public void busquedaPersonalizada(int id) {

    }

    @Override
    public void desplegarEvolucion(Pokemon pokemon) {

    }

    @Override
    public boolean salir(boolean estado) {
        return false;
    }

    /**
     * Método auxiliar para imprimir.
     * @param string a imprimir.
     */
    public void print(String string){
        System.out.println(string);
    }

    public void input(){
        Scanner scanner = new Scanner(System.in);
    }
}
