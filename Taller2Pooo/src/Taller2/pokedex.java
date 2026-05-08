package Taller2;

import java.util.ArrayList;
//Repositorio de todos los pokemon disponibles en el juego
public class pokedex {

    private ArrayList<pokemon> listaPokemones;

    public pokedex() {
        listaPokemones = new ArrayList<>();
    }

    public void agregarPokemon(pokemon p) {
        listaPokemones.add(p);
    }

    public ArrayList<pokemon> getLista() {
        return listaPokemones;
    }
    // Busca un pokemon por nombre ignorando mayusculas
    public pokemon buscarPorNombre(String nombre) {
        for (pokemon p : listaPokemones) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
    // Retorna todos los pokemon que viven en el habitat indicado
    public ArrayList<pokemon> getPorHabitat(String habitat) {
        ArrayList<pokemon> lista = new ArrayList<>();

        for (pokemon p : listaPokemones) {
            if (p.getHabitat().equalsIgnoreCase(habitat)) {
                lista.add(p);
            }
        }

        return lista;
    }
}