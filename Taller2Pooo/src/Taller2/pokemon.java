package Taller2;
//representa un pokemon con stats y etc 
public class pokemon {
    private String nombre;
    private String tipo;
    private int vida;
    private int ataque;
    private int defensa;
    private int ataqueEsp;
    private int defensaEsp;
    private int velocidad;
    private String estado;
	private String habitat;
	private double probabilidad; 
	// Retorna la suma de todas las estadísticas del Pokémon
    public pokemon(String nombre, String habitat, double prob, int vida, int ataque, int defensa, int ataqueEsp, int defensaEsp, int velocidad, String tipo) {
    	this.nombre = nombre;
    	this.habitat = habitat;
    	this.probabilidad = prob;
    	this.vida = vida;
    	this.ataque = ataque;
    	this.defensa = defensa;
    	this.ataqueEsp = ataqueEsp;
    	this.defensaEsp = defensaEsp;
    	this.velocidad = velocidad;
    	this.tipo = tipo;
}
    

    public int getStatsTotales() {
        return vida + ataque + defensa + ataqueEsp + defensaEsp + velocidad;
    }

    public boolean estaVivo() {
        return estado.equalsIgnoreCase("Vivo");
    }
 // Cambia el estado a Debilitado
    public void debilitar() {
        estado = "Debilitado";
    }
 // Cambia el estado a Vivo
    public void curar() {
        estado = "Vivo";
    }

    public String getNombre() { 
    	return nombre; 
    	}
    public String getTipo() { 
    	return tipo; 
    	}
    public String getEstado() { 
    	return estado; 
    	}
    // Muestra nombre, tipo y stats totales
    @Override
    public String toString() {
        return nombre + " | " + tipo + " | Stats: " + getStatsTotales() + " | " + estado;
    }

	public String getHabitat() {
		return habitat;
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public int getVida() {
		return vida;
	}

	public int getAtaque() {
		return ataque;
	}


	public int getDefensa() {
		return defensa;
	}

	public int getAtaqueEsp() {
		return ataqueEsp;
	}

	public int getDefensaEsp() {
		return defensaEsp;
	}

	public int getVelocidad() {
		return velocidad;
	}
}