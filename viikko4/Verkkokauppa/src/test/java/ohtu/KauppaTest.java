package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.task.AsyncTaskExecutor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.common.annotations.VisibleForTesting;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        
        k = new Kauppa(varasto, pankki, viite);              
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void kaksiEriTuotettaVeloitetaanOikein() {
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(8));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void kaksiSamaaTuotettaVeloitetaanOikein() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void yksiTuoteLoppuVeloitetaanOikein() {
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 3));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli mehu 
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksen() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        
        // tehdään ostokset
        k.aloitaAsiointi();     // uusi ostoskori
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void uusiViitenumeroUuudelleOstokselle() {
        Pankki pankki2 = mock(Pankki.class);

        Viitegeneraattori viite2 = mock(Viitegeneraattori.class);

        Varasto varasto2 = mock(Varasto.class);
        when(varasto2.saldo(1)).thenReturn(10); 
        when(varasto2.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k2 = new Kauppa(varasto2, pankki2, viite2);              
        
        k2.aloitaAsiointi();
        k2.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        
        // tehdään ostok2set
        k2.aloitaAsiointi();   // uusi ostoskori
        k2.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k2.tilimaksu("pekka", "12345");

        verify(viite2, times(1)).uusi();
        
        k2.aloitaAsiointi();
        k2.lisaaKoriin(1);     // ostetaan tuotetta numero 2 eli mehua 
        k2.tilimaksu("pekka", "12345");

        verify(viite2, times(2)).uusi();
    }

    @Test
    public void poistaKoristaPalauttaaTuotteenVarastoon() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.poistaKorista(1);
        
        verify(varasto, times(1)).palautaVarastoon(new Tuote(1, "maito", 5));   

    }

    
    
    
    
}