package centraleelettrica;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

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
        try {
            stmt2 = conn.createStatement();
            ResultSet rs = stmt2.executeQuery(qrySel);
            ResultSetMetaData rsmd = rs.getMetaData();

            String[] column = new String[rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                column[i - 1] = rsmd.getColumnName(i);
            }

            ArrayList<String[]> row = new ArrayList<>();

            while (rs.next()) {
                String[] riga = new String[column.length];
                for (int i = 0; i < riga.length; i++) {
                    riga[i] = rs.getString(column[i]);
                }
                row.add(riga);
            }
            String[][] value = new String[row.size()][column.length];
            for (int i = 0; i < row.size(); i++) {
                value[i] = row.get(i);
            }

            rel = new Relazione(column, value);
            //Gui.console.setText(qrySel+"\nEseguita correttamente");
        } catch (SQLException se) {
            System.out.println("ERRORE durante SELECT");
            System.out.println("Codice Errore: " + se.getErrorCode() + " " + se.getMessage());
            Gui.console.setText(se.getErrorCode() + " " + se.getMessage());
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

    public void sendRemove(String delete) {
        try {
            PreparedStatement st = conn.prepareStatement(delete);
            st.executeUpdate();
            //System.out.println("\n\nDELETE eseguita con successo.");
            Gui.console.setText(delete+"\nEseguita correttamente");
        } catch (SQLException se) {
            System.out.println("ERRORE durante DELETE");
            System.out.println("Codice Errore: " + se.getErrorCode() + " " + se.getMessage());
            Gui.console.setText(se.getErrorCode() + " " + se.getMessage());
        }
    }

    public void sendInsert(String query) {
        Statement stmt1 = null;
        try {
            stmt1 = conn.createStatement();
            stmt1.executeUpdate(query);
            Gui.console.setText(query+"\nEseguita correttamente");
        } catch (SQLException se) {
            System.out.println("ERRORE durante INSERT INTO");
            System.out.println("Codice Errore: " + se.getErrorCode() + " " + se.getMessage());
            Gui.console.setText(se.getErrorCode() + " " + se.getMessage());
        }
    }

    public static String toDelete(String[] valoriScomposti, String[] columnName) {
        String delete = "DELETE FROM " + Gui.table + " WHERE ";
        for (int i = 0; i < valoriScomposti.length; i++) {
            delete += Gui.table + "." + columnName[i] + "=";
            delete += (valoriScomposti[i].charAt(0) <= '9' && valoriScomposti[i].charAt(0) >= '0' ? valoriScomposti[i] : "'" + valoriScomposti[i] + "'") + " ";
            if (i < valoriScomposti.length - 1) {
                delete += "AND ";
            }
        }

        System.out.println("DELETE: " + delete);

        return delete;
    }

    public static String toInsert(String[] valoriScomposti, ArrayList<JTextArea> listaAree) {
        String query = "insert into " + Gui.table + " values (";
        for (int i = 0; i < valoriScomposti.length; i++) {
            query += (listaAree.get(i).getText().charAt(0) >= '0' && listaAree.get(i).getText().charAt(0) <= '9' ? listaAree.get(i).getText() : "'" + listaAree.get(i).getText() + "'");
            if (i < listaAree.size() - 1) {
                query += ",";
            }
        }
        query += ")";

        System.out.println("INSERT: " + query);

        return query;
    }

}
