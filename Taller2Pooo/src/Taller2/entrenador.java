package Taller2;

import java.util.ArrayList;

public class entrenador {
    protected String nombre;
    protected ArrayList<pokemon> equipo;

    public entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
    }

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