package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

    private int edellinenTulos;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        edellinenTulos = 0;
    }

    @Override
    public void suorita() {
        edellinenTulos = Integer.parseInt(tuloskentta.getText());
        sovellus.miinus(Integer.parseInt(syotekentta.getText()));
        
        int laskunTulos = sovellus.tulos();    
        
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
    }  
    
}