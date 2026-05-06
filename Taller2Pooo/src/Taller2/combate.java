package Taller2;

public class combate {
    public static pokemon pelear(pokemon p1, pokemon p2) {

        int stats1 = p1.getStatsTotales();
        int stats2 = p2.getStatsTotales();

        System.out.println(p1.getNombre() + " -> " + stats1);
        System.out.println(p2.getNombre() + " -> " + stats2);

        if (stats1 >= stats2) {
            p2.debilitar();
            return p1;
        } else {
            p1.debilitar();
            return p2;
        }
    }
}

