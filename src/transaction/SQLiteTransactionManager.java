/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

import transaction.DBTransactionManager;
import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import sqlite.SQLiteConnect;

/**
 *
 * @author user
 */
public class SQLiteTransactionManager implements DBTransactionManager{

    Connection conn = SQLiteConnect.getConnection();
    
    
    public List<Transaction> viewTransactions() {
        PreparedStatement st;
        String query;
        ResultSet rs;
        ArrayList<Transaction> transactionList = new ArrayList<>();
        try {
            
            query = "SELECT * FROM `Transactions`";
            st = conn.prepareStatement(query);
            
            rs = st.executeQuery();
                
            while(rs.next()){
                //convert string date to LocalDate
                String localDate = rs.getString(2);
                Instant instant = Instant.parse(localDate);
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
                LocalDate date = dateTime.toLocalDate();
                //name
                String name = rs.getString(3);
                //haircut type
                String haircut = rs.getString(4);
                //amount paid
                BigDecimal amount = rs.getBigDecimal(5);
                    
                Transaction tr = new Transaction(date, name, haircut, amount);
                //add the information to an array list
                transactionList.add(tr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return transactionList;
    }

    @Override
    public List<Transaction> viewTransactions(int month, int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> viewTransaction(int month, int day, int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addTransaction(Transaction t) {
        PreparedStatement st;
        String query;

        try {
            //converts the current date and time (LocalDateTime) format into a pattern readable by ViewTransactions()
            LocalDateTime localdatetime = LocalDateTime.now();  
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            String formatDateTime = localdatetime.format(format);
            
            query = "INSERT INTO 'Transactions' (Date, Name, Haircut, Amount) VALUES('"+formatDateTime+"', '"+t.getName()+"', '"+t.getHaircut()+"', '"+t.getAmount()+"')";
            st = conn.prepareStatement(query);
            
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BigDecimal getMonthlySales(int month, int year) {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        PreparedStatement st;
        String query;
        ResultSet rs;
        try {
            query = "SELECT SUM(Amount) FROM 'Transactions' WHERE strftime('%m', Date) = '" + months[month - 1] + "' " + "AND strftime('%Y', Date) = '" + year + "'";
            
            st = conn.prepareStatement(query);
            
            rs = st.executeQuery();
              
            rs.next();
            BigDecimal amount = rs.getBigDecimal(1);
            
            return amount;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteTransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return null;
    }
}
