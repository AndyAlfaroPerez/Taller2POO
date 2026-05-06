package Taller2;

import java.util.ArrayList;

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

    public pokemon buscarPorNombre(String nombre) {
        for (pokemon p : listaPokemones) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

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