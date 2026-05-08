package Taller2;

import java.util.ArrayList;
//Extiende de entrenador, representa al juador con medallas y pc 
public class jugador extends entrenador {
    private int medallas;
    private String ultimaVictoria = "none";
    private ArrayList<pokemon> pc;
    // Inicializa jugador sin medallas y PC vacío
    public jugador(String nombre) {
        super(nombre);
        this.medallas = 0;
        this.pc = new ArrayList<>();
    }
    //Si el equipo tiene menos de 6 se agrega 
    public void capturarPokemon(pokemon p) {
        if (equipo.size() < 6) {
            equipo.add(p);
        } else {
            pc.add(p);
        }
    }
    //Cura los pokemons 
    public void curarEquipo() {
        for (pokemon p : equipo) {
            p.curar();
        }
    }
    // Suma una medalla y guarda el nombre del líder derrotado
    public void ganarMedalla(String lider) {
    	medallas++;
    	this.ultimaVictoria = lider;
    }

    public String getUltimaVictoria() {
		return ultimaVictoria;
	}

	public int getMedallas() {
        return medallas;
    }

    public ArrayList<pokemon> getPC() {
        return pc;
    }
}