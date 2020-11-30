package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {

    protected TextField tuloskentta; 
    protected TextField syotekentta; 
    protected Button plus;
    protected Button miinus;
    protected Button nollaa;
    protected Sovelluslogiikka sovellus;

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.sovellus = sovellus;
    }

    public abstract void suorita();
    
    public void peru() {
        syotekentta.setText("");
        sovellus.edellinen();
        tuloskentta.setText("" + sovellus.tulos());
    }  
}