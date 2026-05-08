package Taller2;

import java.util.ArrayList;
//Representa un gimnasio con su lider, estado y equipo de Pokémon
public class gimnasio {
    private int numero;
    private String lider;
    private String estado;
    private ArrayList<pokemon> pokemons;

    public gimnasio(int numero, String lider) {
        this.numero = numero;
        this.lider = lider;
        this.estado = "Sin derrotar";
        this.pokemons = new ArrayList<>();
    }

    public void agregarPokemon(pokemon p) {
        pokemons.add(p);
    }

    public ArrayList<pokemon> getPokemons() {
        return pokemons;
    }

    public int getNumero() {
		return numero;
	}

	public String getEstado() {
        return estado;
    }
	// Marca el gimnasio como derrotado
    public void derrotar() {
        estado = "Derrotado";
    }

    public String getLider() {
        return lider;
    }
}