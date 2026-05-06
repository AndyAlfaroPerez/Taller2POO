package Taller2;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        pokedex dex = cargarPokedex();
        jugador jugador = null;

        int opcion;

        do {
            System.out.println("1) Continuar\n"
              + "2) Nueva Partida\n"
              + "3) Salir");

            opcion = leerInt(scan);

            if (opcion == 1) {
                jugador = cargarPartida(dex);

                if (jugador != null) {
                    System.out.println("Bienvenido " + jugador.getNombre());
                    menuJuego(scan, jugador, dex);
                }
            }

            if (opcion == 2) {
                System.out.print("Ingrese su apodo: ");
                String nombre = scan.nextLine();

                jugador = new jugador(nombre);
                menuJuego(scan, jugador, dex);
            }

        } while (opcion != 3);
    }

    public static void menuJuego(Scanner scan, jugador jugador, pokedex dex) {
        int op;

        do {
            System.out.println(
                "\n1) Revisar equipo\n"
              + "2) Salir a capturar\n"
              + "3) Acceso al PC (cambiar Pokémon del equipo)\n"
              + "4) Retar un gimnasio\n"
              + "5) Desafío al Alto Mando\n"
              + "6) Curar Pokémon\n"
              + "7) Guardar\n"
              + "8) Guardar y Salir");

            op = leerInt(scan);

            if (op == 1) {
                for (pokemon p : jugador.getEquipo()) {
                    System.out.println(p);
                }
            }

            if (op == 2) {
                salirACapturar(scan, jugador, dex);
            }

            if (op == 6) {
                jugador.curarEquipo();
                System.out.println("Equipo curado!");
            }

            if (op == 7) {
                guardarPartida(jugador);
                System.out.println("Partida guardada!");
            }

            if (op == 8) {
                guardarPartida(jugador);
                System.out.println("Guardado... Saliendo del juego");
            }

        } while (op != 8);
    }

    public static void salirACapturar(Scanner scan, jugador jugador, pokedex dex) {

        System.out.println(
            "\nZonas disponibles:\n"
          + "1) Lago\n"
          + "2) Cueva\n"
          + "3) Montaña\n"
          + "4) Bosque\n"
          + "5) Prado\n"
          + "6) Mar");

        int op = leerInt(scan);

        String habitat = "";

        if (op == 1) habitat = "Lago";
        if (op == 2) habitat = "Cueva";
        if (op == 3) habitat = "Montaña";
        if (op == 4) habitat = "Bosque";
        if (op == 5) habitat = "Prado";
        if (op == 6) habitat = "Mar";

        ArrayList<pokemon> lista = dex.getPorHabitat(habitat);

        if (lista.size() == 0) {
            System.out.println("No hay pokemones aquí");
            return;
        }

        Random r = new Random();
        pokemon encontrado = lista.get(r.nextInt(lista.size()));

        System.out.println("Apareció: " + encontrado.getNombre());

        System.out.println(
            "\n¿Qué deseas hacer?\n"
          + "1) Capturar\n"
          + "2) Huir");

        int decision = leerInt(scan);

        if (decision == 1) {
            jugador.capturarPokemon(encontrado);
            System.out.println("Capturado!");
        } else {
            System.out.println("Huiste...");
        }
    }

    public static pokedex cargarPokedex() {
        pokedex dex = new pokedex();

        try {
            Scanner leer = new Scanner(new File("Pokedex.txt"));

            while (leer.hasNextLine()) {
                String[] partes = leer.nextLine().split(";");

                pokemon p = new pokemon(
                    partes[0],
                    partes[1],
                    Double.valueOf(partes[2]),
                    Integer.valueOf(partes[3]),
                    Integer.valueOf(partes[4]),
                    Integer.valueOf(partes[5]),
                    Integer.valueOf(partes[6]),
                    Integer.valueOf(partes[7]),
                    Integer.valueOf(partes[8]),
                    partes[9]
                );

                p.curar();
                dex.agregarPokemon(p);
            }

            leer.close();

        } catch (Exception e) {
            System.out.println("Error cargando pokedex");
        }

        return dex;
    }

    public static void guardarPartida(jugador jugador) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt"));

            bw.write(jugador.getNombre() + ";" + jugador.getMedallas());
            bw.newLine();

            for (pokemon p : jugador.getEquipo()) {
                bw.write(p.getNombre() + ";" + p.getEstado());
                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {
            System.out.println("Error al guardar");
        }
    }

    public static jugador cargarPartida(pokedex dex) {

        try {
            Scanner leer = new Scanner(new File("Registros.txt"));

            if (!leer.hasNextLine()) {
                System.out.println("No hay partida guardada");
                return null;
            }

            String[] datos = leer.nextLine().split(";");
            jugador j = new jugador(datos[0]);

            while (leer.hasNextLine()) {
                String[] partes = leer.nextLine().split(";");

                pokemon base = dex.buscarPorNombre(partes[0]);

                if (base != null) {
                    pokemon nuevo = new pokemon(
                        base.getNombre(),
                        base.getHabitat(),
                        base.getProbabilidad(),
                        base.getVida(),
                        base.getAtaque(),
                        base.getDefensa(),
                        base.getAtaqueEsp(),
                        base.getDefensaEsp(),
                        base.getVelocidad(),
                        base.getTipo()
                    );

                    if (partes[1].equalsIgnoreCase("Vivo")) {
                        nuevo.curar();
                    } else {
                        nuevo.debilitar();
                    }

                    j.capturarPokemon(nuevo);
                }
            }

            leer.close();
            return j;

        } catch (Exception e) {
            System.out.println("Error cargando partida");
            return null;
        }
    }

    public static int leerInt(Scanner scan) {
        while (true) {
            try {
                return Integer.valueOf(scan.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un numero valido:");
            }
        }
    }
}