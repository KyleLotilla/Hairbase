/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hairplay.hairbase.customerListing;

import java.io.IOException;
import com.hairplay.hairbase.transaction.SQLiteTransactionManager;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import static javax.accessibility.AccessibleRole.ALERT;

/**
 *
 * @author Terence
 */
public class ExportTransactionButtonController {
    public void exportTransactionReport() throws IOException, SQLException {
        PreparedStatement st;
        String query;
        ResultSet rs;
        String filename = "Transactions" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".csv";
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/sofengg.db");
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {
            query = "SELECT * FROM `Transactions`";
            st = conn.prepareStatement(query); 
            rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            FileWriter fw = new FileWriter(filename);
            fw.append(rsmd.getColumnLabel(2));
            fw.append(',');
            fw.append(rsmd.getColumnLabel(3));
            fw.append(',');
            fw.append(rsmd.getColumnLabel(4));
            fw.append(',');
            fw.append(rsmd.getColumnLabel(5));
            fw.append(',');     
            fw.append('\n');
            while(rs.next()){          
                fw.append(rs.getString(2).substring(0, 10));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            conn.close();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EXPORT SUCCESSFUL!");
            alert.setHeaderText("You may open it using any text editor (MS Excel recommended)");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
