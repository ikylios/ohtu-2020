
package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int[] luvut;
    private int kasvatusKoko; 
    private int alkioidenLkm;  

    public IntJoukko() {
        luvut = new int[5];
        kasvatusKoko = 5;
    }

    public IntJoukko(int koko) {
        luvut = new int[koko];
        kasvatusKoko = 5;
    }
    
    public IntJoukko(int koko, int kasvuKoko) {
        luvut = new int[koko];
        kasvatusKoko = kasvuKoko;
    }
    
    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true; 
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % luvut.length == 0) {
                int[] uusiLuvut = new int[alkioidenLkm + kasvatusKoko];
                kopioiTaulukko(luvut, uusiLuvut);
                luvut = uusiLuvut;
            }
            return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int poistettavanIndeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                poistettavanIndeksi = i;
                break;
            }
        }

        if (poistettavanIndeksi != -1) {
            for (int i = poistettavanIndeksi; i < alkioidenLkm-1; i++) {
                luvut[i] = luvut[i + 1];
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        if (alkioidenLkm != 0) {
            for (int i = 0; i < alkioidenLkm; i++) {
                tuotos += "" + luvut[i];
                if (i < alkioidenLkm - 1) {
                    tuotos += ", ";
                }
            }
        }
        tuotos += "}";
       
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    x.lisaa(bTaulu[j]);
                }
            }
        }
        return x;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.poista(bTaulu[i]);
        }
 
        return x;
    }

        
}
