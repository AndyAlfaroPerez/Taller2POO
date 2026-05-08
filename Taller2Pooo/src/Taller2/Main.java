//Andy Alejandro Alfaro Perez 21.918.973-7 ICCI
package Taller2;
//Importar
import java.util.*;
import java.io.*;

public class Main {
	//Muestra el menu principal, crea o continua una partida
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        pokedex dex = cargarPokedex();

        jugador jugador = null;

        int opcion;

        do {

            System.out.println("Bienvenido al juego Pokemon!!\n"
                    + "1) Continuar.\n"
                    + "2) Nueva Partida.\n"
                    + "3) Salir.");

            System.out.print("Ingrese Opcion: ");

            opcion = leerInt(scan);

            if (opcion == 1) {

                jugador = cargarPartida(dex);

                if (jugador != null) {

                    System.out.println("Bienvenido "
                            + jugador.getNombre()
                            + "!!");

                    menuJuego(scan, jugador, dex);
                }
            }

            if (opcion == 2) {

                System.out.print("Ingrese Apodo: ");

                String nombre = scan.nextLine();

                jugador = new jugador(nombre);

                System.out.println("Bienvenido "
                        + jugador.getNombre()
                        + "!!");

                menuJuego(scan, jugador, dex);
            }

        } while (opcion != 3);

        System.out.println("Nos vemos entrenador...");
    }
    //Submenu que permite elegirt entre las posibles acciones de juego
    public static void menuJuego(Scanner scan, jugador jugador, pokedex dex) {

        int op;

        do {

            System.out.println("\n"
                    + jugador.getNombre()
                    + ", que deseas hacer?\n\n"
                    + "1) Revisar equipo.\n"
                    + "2) Salir a capturar.\n"
                    + "3) Acceso al PC (cambiar Pokémon del equipo).\n"
                    + "4) Retar un gimnasio.\n"
                    + "5) Desafío al Alto Mando.\n"
                    + "6) Curar Pokémon.\n"
                    + "7) Guardar.\n"
                    + "8) Guardar y Salir.");

            System.out.print("Ingrese Opcion: ");

            op = leerInt(scan);

            if (op == 1) {
                revisarEquipo(jugador);
            }

            if (op == 2) {
                salirACapturar(scan, jugador, dex);
            }

            if (op == 3) {
                accesoPC(scan, jugador);
            }

            if (op == 4) {
                retarGimnasio(scan, jugador, dex);
            }

            if (op == 5) {
                desafioAltoMando(scan, jugador, dex);
            }

            if (op == 6) {

                jugador.curarEquipo();

                System.out.println("Tu equipo se ha recuperado!");
            }

            if (op == 7) {

                guardarPartida(jugador);

                System.out.println("Partida guardada con exito!");
            }

            if (op == 8) {

                guardarPartida(jugador);

                System.out.println("Nos vemos entrenador...");
            }

        } while (op != 8);
    }
    //Muestra el equipo actual 
    public static void revisarEquipo(jugador jugador) {

        System.out.println("\nEquipo Actual:");

        if (jugador.getEquipo().size() == 0) {

            System.out.println("No tienes pokemones en tu equipo!");
            return;
        }

        for (int i = 0; i < jugador.getEquipo().size(); i++) {

            pokemon p = jugador.getEquipo().get(i);

            System.out.println((i + 1)
                    + ") "
                    + p);
        }
    }
    //Permite explorar y capturar pokemons
    public static void salirACapturar(Scanner scan, jugador jugador, pokedex dex) {

        System.out.println("\nDonde deseas ir a explorar?\n\n"
                + "Zonas disponibles:\n\n"
                + "1) Lago\n"
                + "2) Cueva\n"
                + "3) Montaña\n"
                + "4) Bosque\n"
                + "5) Prado\n"
                + "6) Mar\n"
                + "7) Volver al menu.");

        System.out.print("Ingrese Zona: ");

        int op = leerInt(scan);

        if (op == 7) {
            return;
        }

        String habitat = "";

        if (op == 1) habitat = "Lago";
        if (op == 2) habitat = "Cueva";
        if (op == 3) habitat = "Montaña";
        if (op == 4) habitat = "Bosque";
        if (op == 5) habitat = "Prado";
        if (op == 6) habitat = "Mar";

        ArrayList<pokemon> lista = dex.getPorHabitat(habitat);

        if (lista.size() == 0) {

            System.out.println("No hay pokemones aqui!");
            return;
        }
        //Seleccion aleotoria con %
        Random r = new Random();
        double probabilidad = r.nextDouble();
        double acumulado = 0.0;
        pokemon encontrado = lista.get(0);

        for (pokemon p : lista) {
            acumulado += p.getProbabilidad();
            if (probabilidad <= acumulado) {
                encontrado = p;
                break;
            }
        }
        //verifica que no se haya capturado 
        for (pokemon p : jugador.getEquipo()) {

            if (p.getNombre().equalsIgnoreCase(encontrado.getNombre())) {

                System.out.println("Ya capturaste este pokemon!");
                return;
            }
        }

        for (pokemon p : jugador.getPC()) {

            if (p.getNombre().equalsIgnoreCase(encontrado.getNombre())) {

                System.out.println("Ya capturaste este pokemon!");
                return;
            }
        }

        System.out.println("\nOh!! Ha aparecido un increible "
                + encontrado.getNombre()
                + "!!");

        System.out.println("\nQue deseas hacer?\n\n"
                + "1) Capturar\n"
                + "2) Huir");

        System.out.print("Ingrese Opcion: ");

        int decision = leerInt(scan);

        if (decision == 1) {

            jugador.capturarPokemon(encontrado);

            System.out.println(encontrado.getNombre()
                    + " capturado con exito!!");

            if (jugador.getEquipo().contains(encontrado)) {

                System.out.println(encontrado.getNombre()
                        + " ha sido agregado a tu equipo!");

            } else {

                System.out.println(encontrado.getNombre()
                        + " ha sido enviado al PC!");
            }
        }

        if (decision == 2) {

            System.out.println("Has huido...");
        }
    }
    //Muestra lo pokemons y intercambiar 
    public static void accesoPC(Scanner scan, jugador jugador) {

        ArrayList<pokemon> lista = new ArrayList<>();

        for (pokemon p : jugador.getEquipo()) {
            lista.add(p);
        }

        for (pokemon p : jugador.getPC()) {
            lista.add(p);
        }

        System.out.println("\nPokemones capturados:\n");

        for (int i = 0; i < lista.size(); i++) {

            System.out.println((i + 1)
                    + ") "
                    + lista.get(i));
        }

        System.out.println("\n1) Cambiar Pokemon\n"
                + "2) Salir");

        System.out.print("Ingrese Opcion: ");

        int op = leerInt(scan);

        if (op == 1) {

            System.out.print("Ingrese primer pokemon: ");
            int a = leerInt(scan) - 1;

            System.out.print("Ingrese segundo pokemon: ");
            int b = leerInt(scan) - 1;

            if (a >= 0
                    && b >= 0
                    && a < lista.size()
                    && b < lista.size()) {

                pokemon aux = lista.get(a);

                lista.set(a, lista.get(b));
                lista.set(b, aux);
                //Redistibuye los primeros 6 al equipo el resto al pc 
                jugador.getEquipo().clear();
                jugador.getPC().clear();

                for (int i = 0; i < lista.size(); i++) {

                    if (i < 6) {
                        jugador.getEquipo().add(lista.get(i));
                    } else {
                        jugador.getPC().add(lista.get(i));
                    }
                }

                System.out.println("Pokemones cambiados con exito!");
            }
        }
    }
    //lee txt, muestra estado y gestona combate 
    public static void retarGimnasio(Scanner scan, jugador jugador, pokedex dex) {

        try {

            Scanner leer = new Scanner(new File("Gimnasios.txt"));

            ArrayList<String> lineas = new ArrayList<>();

            System.out.println("\nA cual Lider deseas retar??\n");

            while (leer.hasNextLine()) {

                String linea = leer.nextLine();

                lineas.add(linea);

                String[] partes = linea.split(";");

                System.out.println(partes[0]
                        + ") "
                        + partes[1]
                        + " - Estado: "
                        + partes[2]);
            }

            System.out.println("9) Volver al menu.");

            System.out.print("Ingrese Opcion: ");

            int op = leerInt(scan);

            if (op == 9) {
                return;
            }
            //verifica que haya orden 
            if (op > jugador.getMedallas() + 1) {

                System.out.println("Calmado Entrenador!!! No puedes retar a ese lider sin haber derrotado a los lideres anteriores!!");
                return;
            }

            String[] datos = lineas.get(op - 1).split(";");

            String lider = datos[1];

            System.out.println("\nDesafiando a "
                    + lider
                    + "!!");

            for (int i = 4; i < datos.length; i++) {

                pokemon rival = dex.buscarPorNombre(datos[i]);

                System.out.println(lider
                        + " saca a "
                        + rival.getNombre()
                        + "!");

                pokemon mio = primerPokemonVivo(jugador);

                if (mio == null) {

                    System.out.println("Te has quedado sin pokemons en tu equipo!");
                    System.out.println("Volviendo al menu...");
                    return;
                }

                System.out.println(jugador.getNombre()
                        + " saca a "
                        + mio.getNombre()
                        + "!");

                boolean pelea = true;

                while (pelea) {

                    System.out.println("\nQue deseas hacer?\n"
                            + "1) Atacar\n"
                            + "2) Cambiar de pokemon\n"
                            + "3) Rendirse");

                    System.out.print("Ingrese Opcion: ");

                    int decision = leerInt(scan);

                    if (decision == 1) {

                        int statsJugador = mio.getStatsTotales();
                        int statsRival = rival.getStatsTotales();

                        System.out.println(mio.getNombre()
                                + " -> "
                                + statsJugador
                                + " puntos");

                        System.out.println(rival.getNombre()
                                + " -> "
                                + statsRival
                                + " puntos");
                        //aplica multiplicador usando la tabla de tipos 
                        double mult = tablatipos.getEfectividad(mio.getTipo(), rival.getTipo());

                        if (mult == 2.0) {
                            System.out.println(mio.getNombre() + " es efectivo contra " + rival.getNombre() + "!");
                            statsJugador = (int)(statsJugador * 2.0);
                        }
                        if (mult == 0.5) {
                            System.out.println(mio.getNombre() + " no es efectivo contra " + rival.getNombre() + "!");
                            statsJugador = (int)(statsJugador * 0.5);
                        }

                        System.out.println("Nuevo puntaje:");

                        System.out.println(mio.getNombre()
                                + " -> "
                                + statsJugador
                                + " puntos");

                        System.out.println(rival.getNombre()
                                + " -> "
                                + statsRival
                                + " puntos");

                        if (statsJugador >= statsRival) {

                            rival.debilitar();

                            System.out.println("Ha ganado "
                                    + mio.getNombre()
                                    + "!");

                        } else {

                            mio.debilitar();

                            System.out.println("Ha ganado "
                                    + rival.getNombre()
                                    + "! "
                                    + mio.getNombre()
                                    + " ha sido derrotado...");
                        }

                        pelea = false;
                    }

                    if (decision == 2) {

                        for (int j = 0; j < jugador.getEquipo().size(); j++) {

                            pokemon p = jugador.getEquipo().get(j);

                            System.out.println((j + 1)
                                    + ") "
                                    + p.getNombre()
                                    + " - "
                                    + p.getEstado());
                        }

                        int cambio = leerInt(scan) - 1;

                        if (cambio >= 0
                                && cambio < jugador.getEquipo().size()) {

                            pokemon nuevo = jugador.getEquipo().get(cambio);

                            if (nuevo.estaVivo()) {

                                mio = nuevo;

                                System.out.println("Vas "
                                        + mio.getNombre());
                            }
                        }
                    }

                    if (decision == 3) {

                        System.out.println("Te has rendido...");
                        return;
                    }
                }
            }

            System.out.println("\nHas derrotado al lider "
                    + lider
                    + "!!");
            //Guarda lka medalla 
            jugador.ganarMedalla(lider);

            datos[2] = "Derrotado";

            lineas.set(op - 1,
                    datos[0]
                            + ";"
                            + datos[1]
                            + ";"
                            + datos[2]
                            + ";"
                            + datos[3]
                            + ";"
                            + String.join(";",
                            Arrays.copyOfRange(datos,
                                    4,
                                    datos.length)));

            BufferedWriter bw = new BufferedWriter(new FileWriter("Gimnasios.txt"));

            for (String l : lineas) {

                bw.write(l);
                bw.newLine();
            }

            bw.close();
            leer.close();

        } catch (Exception e) {

            System.out.println("Error leyendo gimnasios");
        }
    }
    //lee txt, gestiona combates sin volver al menu 
    public static void desafioAltoMando(Scanner scan, jugador jugador, pokedex dex) {

        if (jugador.getMedallas() < 8) {

            System.out.println("Necesitas las 8 medallas para entrar al Alto Mando!");
            return;
        }

        try {

            Scanner leer = new Scanner(new File("Alto Mando.txt"));

            while (leer.hasNextLine()) {

                String[] partes = leer.nextLine().split(";");

                String nombre = partes[1];

                System.out.println("\nTe enfrentas a "
                        + nombre
                        + "!!");

                for (int i = 2; i < partes.length; i++) {

                    pokemon rival = dex.buscarPorNombre(partes[i]);

                    pokemon mio = primerPokemonVivo(jugador);

                    if (mio == null) {

                        System.out.println("Has perdido contra el Alto Mando...");
                        leer.close();
                        return;
                    }

                    System.out.println(nombre
                            + " saca a "
                            + rival.getNombre()
                            + "!");

                    boolean pelea = true;

                    while (pelea) {

                        System.out.println("\n1) Atacar\n"
                                + "2) Cambiar Pokemon\n"
                                + "3) Rendirse");

                        System.out.print("Ingrese Opcion: ");

                        int op = leerInt(scan);

                        if (op == 1) {

                            int statsJugador = mio.getStatsTotales();
                            int statsRival = rival.getStatsTotales();

                            System.out.println(mio.getNombre() + " -> " + statsJugador + " puntos");
                            System.out.println(rival.getNombre() + " -> " + statsRival + " puntos");

                            double mult = tablatipos.getEfectividad(mio.getTipo(), rival.getTipo());

                            if (mult == 2.0) {
                                System.out.println(mio.getNombre() + " es efectivo contra " + rival.getNombre() + "!");
                                statsJugador = (int)(statsJugador * 2.0);
                            }

                            if (mult == 0.5) {
                                System.out.println(mio.getNombre() + " no es efectivo contra " + rival.getNombre() + "!");
                                statsJugador = (int)(statsJugador * 0.5);
                            }

                            System.out.println("Nuevo puntaje:");
                            System.out.println(mio.getNombre() + " -> " + statsJugador + " puntos");
                            System.out.println(rival.getNombre() + " -> " + statsRival + " puntos");

                            if (statsJugador >= statsRival) {

                                rival.debilitar();
                                System.out.println("Ha ganado " + mio.getNombre() + "!");

                            } else {

                                mio.debilitar();
                                System.out.println("Ha ganado " + rival.getNombre() + "! "
                                        + mio.getNombre() + " ha sido derrotado...");
                            }

                            pelea = false;
                        }
                        

                        if (op == 2) {

                            for (int j = 0; j < jugador.getEquipo().size(); j++) {

                                pokemon p = jugador.getEquipo().get(j);

                                System.out.println((j + 1)
                                        + ") "
                                        + p.getNombre()
                                        + " - "
                                        + p.getEstado());
                            }

                            int cambio = leerInt(scan) - 1;

                            if (cambio >= 0
                                    && cambio < jugador.getEquipo().size()) {

                                pokemon nuevo = jugador.getEquipo().get(cambio);

                                if (nuevo.estaVivo()) {

                                    mio = nuevo;
                                }
                            }
                        }

                        if (op == 3) {

                            System.out.println("Te has rendido...");
                            leer.close();
                            return;
                        }
                    }
                }
            }

            leer.close();

            System.out.println("Felicidades!! Ahora eres campeon Pokemon!");

        } catch (Exception e) {

            System.out.println("Error leyendo Alto Mando");
        }
    } //67
    //retorna el primer pokemon vivo o no si es que perdieron 
    public static pokemon primerPokemonVivo(jugador jugador) {

        for (pokemon p : jugador.getEquipo()) {

            if (p.estaVivo()) {
                return p;
            }
        }

        return null;
    }
    //Sobrescribe datos en registros con el estado actual del jugador y sus pokemons 
    public static void guardarPartida(jugador jugador) {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt"));

            bw.write(jugador.getNombre() + ";" + jugador.getUltimaVictoria());
            bw.newLine();

            for (pokemon p : jugador.getEquipo()) {

                bw.write(p.getNombre()
                        + ";"
                        + p.getEstado());

                bw.newLine();
            }

            for (pokemon p : jugador.getPC()) {

                bw.write(p.getNombre()
                        + ";"
                        + p.getEstado());

                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {

            System.out.println("Error al guardar partida");
        }
    }
    //lee el txt registros y continua desde el punto guardado 
    public static jugador cargarPartida(pokedex dex) {

        try {

            Scanner leer = new Scanner(new File("Registros.txt"));

            if (!leer.hasNextLine()) {

                System.out.println("No hay partida guardada!");
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
    //lee la pokedex y carga los pokemons 
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
    //valida que el numero sea entero 
    public static int leerInt(Scanner scan) {

        while (true) {

            try {

                return Integer.valueOf(scan.nextLine());

            } catch (Exception e) {

                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}

