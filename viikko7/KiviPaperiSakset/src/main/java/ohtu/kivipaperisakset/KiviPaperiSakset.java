package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {
    private static final Scanner scanner = new Scanner(System.in);
    String ekanSiirto;
    String tokanSiirto;
    
    // tämä on ns template metodi
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        // ...
        
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        ekanSiirto = "p";
        tokanSiirto = "p";
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            
            toisenSiirto();

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected String ensimmaisenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    // tämä on abstrakti metodi sillä sen toteutus vaihtelee eri pelityypeissä
    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}