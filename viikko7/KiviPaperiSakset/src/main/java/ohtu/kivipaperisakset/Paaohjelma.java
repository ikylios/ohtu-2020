package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean pelataan = true;

        while (pelataan) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();

            switch(vastaus) {
                case "a":
                    KPSPelaajaVsPelaaja kaksinpeli = new KPSPelaajaVsPelaaja();
                    kaksinpeli.pelaa();
                    break;
                case "b":
                    KPSTekoaly yksinpeli = new KPSTekoaly();
                    yksinpeli.pelaa();
                    break;
                case "c":
                    KPSParempiTekoaly pahaYksinpeli = new KPSParempiTekoaly();
                    pahaYksinpeli.pelaa();
                    break;
                default:
                    pelataan = false;
                    break;
            }

        }

    }
}
