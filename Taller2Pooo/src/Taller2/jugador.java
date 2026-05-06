package Taller2;

import java.util.ArrayList;

public class jugador extends entrenador {
    private int medallas;
    private ArrayList<pokemon> pc;

    public jugador(String nombre) {
        super(nombre);
        this.medallas = 0;
        this.pc = new ArrayList<>();
    }

    public void capturarPokemon(pokemon p) {
        if (equipo.size() < 6) {
            equipo.add(p);
        } else {
            pc.add(p);
        }
    }

    public void curarEquipo() {
        for (pokemon p : equipo) {
            p.curar();
        }
    }

    public int getMedallas() {
        return medallas;
    }

    public void ganarMedalla() {
        medallas++;
    }

    public ArrayList<pokemon> getPC() {
        return pc;
    }
}