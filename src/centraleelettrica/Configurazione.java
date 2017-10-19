/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centraleelettrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mantini.christian
 */
public class Configurazione {
    
    private static final String USER = "5bin";
    private static final String PASSWORD = "5bin";
    //private static final String ADDRESS = "jdbc:mysql://192.168.1.252:3306/";
    private static final String ADDRESS = "jdbc:mysql://80.22.95.8:3306/";
    private static final int PORT = 3306;
    private static final String DB_NAME = "5bindb";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(ADDRESS+DB_NAME, USER, PASSWORD);
    }
    
}
