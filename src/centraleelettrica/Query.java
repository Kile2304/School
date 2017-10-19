package centraleelettrica;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mantini.christian
 */
public class Query {

    private Connection conn;

    public Query(Connection conn) {
        this.conn = conn;
    }

    public Query() {
        try {
            this.conn = Configurazione.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Relazione sendSelect(String qrySel) {
        Relazione rel = null;
        Statement stmt2 = null;
        //String qrySel = "SELECT * FROM locations";
        //String qrySel = "INSERT INTO departments VALUES (271, 'Jefrry', 888, 2300)";
        try {
            stmt2 = conn.createStatement();
            ResultSet rs = stmt2.executeQuery(qrySel);
            //elaboro il risultato della select
            //System.out.println("\n\nRisultati della select:\n");
            ResultSetMetaData rsmd = rs.getMetaData();

            String[] column = new String[rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                column[i - 1] = rsmd.getColumnName(i);
            }
            /*for (int i = 0; i < column.length; i++) {
                System.out.print("\t" + column[i]);
            }*/
            //System.out.println("");

            ArrayList<String[]> row = new ArrayList<>();

            while (rs.next()) {
                String[] riga = new String[column.length];
                for (int i = 0; i < riga.length; i++) {
                    riga[i] = rs.getString(column[i]);
                    //System.out.print("\t" + riga[i]);
                }
                row.add(riga);
                //System.out.println("");
            }
            String[][] value = new String[row.size()][column.length];
            for (int i = 0; i < row.size(); i++) {
                value[i] = row.get(i);
            }

            rel = new Relazione(column, value);
        } catch (SQLException se) {
            System.out.println("ERRORE durante SELECT");
            System.out.println("Codice Errore: " + se.getErrorCode() + " " + se.getMessage());
        }
        return rel;
    }

    public String[] getTable() {
        String[] tableList = null;
        try {
            ArrayList<String> tableName = new ArrayList<>();
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                tableName.add(rs.getString(3));
            }
            tableList = new String[tableName.size()];
            for (int i = 0; i < tableList.length; i++) {
                tableList[i] = tableName.get(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableList;
    }


    public Relazione sendRemove(String delete) {
        return null;
    }

}
