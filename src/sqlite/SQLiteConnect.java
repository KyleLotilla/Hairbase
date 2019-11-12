/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class SQLiteConnect {
    private static Connection conn;
    
    private SQLiteConnect(){
        
    }
    
    public static Connection getConnection(){
        
        if(conn == null){
            try {
                // db parameters
                String url = "jdbc:sqlite:C:/sqlite/db/sofengg.db";
                // create a connection to the database
                conn = DriverManager.getConnection(url);
            
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
        return conn;
    }
}
