package ohtu.verkkokauppa;

public interface VarastoFace {

    public Tuote haeTuote(int id);

    public int saldo(int id);
    
    public void otaVarastosta(Tuote t); 
    
    public void palautaVarastoon(Tuote t);

}