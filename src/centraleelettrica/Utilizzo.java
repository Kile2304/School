package centraleelettrica;

/**
 *
 * @author ps1
 */
public interface Utilizzo {
    
    void leggiCentrale(Centrale c);
    void leggiProduzione(Centrale c);
    
    boolean getEsci();
    boolean getErrore();
    
    String getFile();
    
    void inizializza(Object ob, boolean delete);
        
}
