package Taller2;

import java.util.ArrayList;
//clase que representa cualqueir entrendador del juego 
public class entrenador {
    protected String nombre;
    protected ArrayList<pokemon> equipo;
    // Inicializa el entrenador con nombre y equipo vacío
    public entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
    }
    // Agrega un Pokémon directamente al equipo
    public void agregarPokemon(pokemon p) {
        equipo.add(p);
    }

    public ArrayList<pokemon> getEquipo() {
        return equipo;
    }

    public String getNombre() {
        return nombre;
    }
}