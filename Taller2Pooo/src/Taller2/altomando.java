package Taller2;

import java.util.ArrayList;

public class altomando {
    private int numero;
    private String nombre;
    private ArrayList<pokemon> pokemons;

    public altomando(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
        this.pokemons = new ArrayList<>();
    }

    public void agregarPokemon(pokemon p) {
        pokemons.add(p);
    }

    public ArrayList<pokemon> getPokemons() {
        return pokemons;
    }

    public String getNombre() {
        return nombre;
    }
}