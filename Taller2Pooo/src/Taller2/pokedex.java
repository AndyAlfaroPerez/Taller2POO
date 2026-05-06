package Taller2;

import java.util.ArrayList;

public class pokedex {

    private ArrayList<pokemon> lista;

    public pokedex() {
        lista = new ArrayList<>();
    }

    public void agregarPokemon(pokemon p) {
        lista.add(p);
    }

    public ArrayList<pokemon> getLista() {
        return lista;
    }

    public pokemon buscarPorNombre(String nombre) {
        for (pokemon p : lista) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<pokemon> filtrarPorHabitat(String habitat) {
        ArrayList<pokemon> resultado = new ArrayList<>();

        for (pokemon p : lista) {
            if (p.getHabitat().equalsIgnoreCase(habitat)) {
                resultado.add(p);
            }
        }

        return resultado;
    }
}