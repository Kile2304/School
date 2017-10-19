package centraleelettrica;

/**
 *
 * @author mantini.christian
 */
public class Produzione {

    private char[] codice;
    private int mese;
    private int anno;
    private double potenza;

    public Produzione(int mese, char[] codice, int anno, double potenza) {
        this.mese = mese;
        this.codice = codice;
        this.anno = anno;
        this.potenza = potenza;
    }

    public Produzione(char[] codice) {
        this.codice = codice;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public char[] getCodice() {
        return codice;
    }

    public int getAnno() {
        return anno;
    }

    public double getPotenza() {
        return potenza;
    }

    public void setMese(String mese) {
        this.mese = Integer.parseInt(mese);
    }

    public void setCodice(char[] codice) {
        this.codice = codice;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setAnno(String anno) {
        this.anno = Integer.parseInt(anno);
    }

    public void setPotenza(double potenza) {
        this.potenza = potenza;
    }

    public void setPotenza(String potenza) {
        this.potenza = Double.parseDouble(potenza);
    }

    public int getMese() {
        return mese;
    }

}
