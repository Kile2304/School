package centraleelettrica;

import java.util.HashMap;

/**
 *
 * @author LENOVO
 */
public class Relazione {
    
    private String[] columnName;
    private String[][] value;
    
    public Relazione(String[] columnName, String[][] value){
        this.columnName = columnName;
        this.value = value;
    }
    
    public String[] getColumnName(){
        return columnName;
    }
    
    public String[][] getValue(){
        return value;
    }
    
}
