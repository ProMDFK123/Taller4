package system;

import list.ListaNodoDoble;
import list.NodoDoble;
import objects.Pokemon;
import java.util.Scanner;

public class SistemaImpl implements Sistema{
    private final ListaNodoDoble pokedex;
    public SistemaImpl(){
        pokedex=new ListaNodoDoble();
        this.menu();
    }

    public void menu(){

    }

    @Override
    public void desplegarPokemon(int idInicio, int idFin) {
        ListaNodoDoble listaAux = new ListaNodoDoble();
        StringBuilder pokemonInfo = new StringBuilder();
        int min=99999;
        int max=-9999;

        for(NodoDoble nodoAux=this.pokedex.getHead(); nodoAux!=null; nodoAux=nodoAux.getNext()){
            if(nodoAux.getElemento() instanceof Pokemon pokemon){
                int auxId = pokemon.getId();
                if(auxId<idFin && auxId>idInicio){
                    listaAux.agregar(pokemon);
                }
            }
        }
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
