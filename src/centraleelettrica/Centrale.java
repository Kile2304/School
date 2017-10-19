package centraleelettrica;

import java.util.ArrayList;

/**
 *
 * @author mantini.christian
 */
public class Centrale {

    private char[] codice;
    private String indirizzo;
    private String prov;
    private double potenza;
    private boolean isActive;
    private ArrayList<Produzione> produzione;

    public char[] getCodice() {
        return codice;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getProv() {
        return prov;
    }

    public double getPotenza() {
        return potenza;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public ArrayList<Produzione> getProduzione() {
        return produzione;
    }

    public Centrale() {
        codice = new char[4];
        produzione = new ArrayList<>();
    }

    public void setCodice(char[] codice) {
        this.codice = codice;
    }

    public void setCodice(String codice) {
        this.codice = codice.toCharArray();
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public void setPotenza(double potenza) {
        this.potenza = potenza;
    }

    public void setPotenza(String potenza) {
        this.potenza = Double.parseDouble(potenza);
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = Boolean.parseBoolean(isActive);
    }

    public void insertProduzione(Produzione produzione) {
        this.produzione.add(produzione);
    }

    public void remProduzione(int mese) {

    }

}
