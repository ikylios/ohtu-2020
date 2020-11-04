package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjaFace {

    public void lisaaTapahtuma(String tapahtuma);

    public ArrayList<String> getTapahtumat();

}