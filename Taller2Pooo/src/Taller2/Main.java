package Taller2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        jugador jugador = null;

        int opcion;

        do {
            System.out.println("1) Nueva Partida\r\n"
            			+ "2) Salir");

            opcion = leerInt(scan);

            if (opcion == 1) {
                System.out.print("Nombre: ");
                String nombre = scan.nextLine();
                jugador = new jugador(nombre);

                menuJuego(scan, jugador);
            }

        } while (opcion != 2);
    }

    public static void menuJuego(Scanner scan, jugador jugador) {
        int op;

        do {
            System.out.println("\n1) Ver equipo\r\n"
            		+ "2) Curar\r\n"
            		+ "3) Salir ");

            op = leerInt(scan);

            if (op == 1) {
                for (pokemon p : jugador.getEquipo()) {
                    System.out.println(p);
                }
            }

            if (op == 2) {
                jugador.curarEquipo();
                System.out.println("Equipo curado!");
            }

        } while (op != 3);
    }

    public static int leerInt(Scanner scan) {
        while (true) {
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println("Ingrese un numero valido:");
            }
        }
    }
}