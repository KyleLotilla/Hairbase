/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hairplay.hairbase.customerListing;

import com.hairplay.hairbase.transaction.DBTransactionManager;
import java.io.IOException;
import com.hairplay.hairbase.transaction.SQLiteTransactionManager;
import com.hairplay.hairbase.transaction.Transaction;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.Stage;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.control.Alert.AlertType; 
import java.time.LocalDate; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;


public class FilterDateTransactionButtonController {
    
    private TransactionTableController transactionTableController;
    private DBTransactionManager transactionManager;
    private ObservableList<Transaction> transactionList;
    
    public void initialize(){}
    
    public void openFilterByDate() throws IOException, SQLException {
        Stage s = new Stage();
         
        s.setTitle("Filter By Date"); 
  
        // create a tile pane 
        TilePane r = new TilePane(); 
  
        // create a date picker 
        DatePicker d = new DatePicker(); 
        Button b = new Button(); 
  
        // add button and label 
        r.getChildren().add(d); 
        r.getChildren().add(b);
        // create a scene 
        Scene sc = new Scene(r, 200, 200); 
  
        // set the scene 
        s.setScene(sc); 
  
        s.show(); 
        
        b.setVisible(true);
        b.setText("Filter");
        
        b.setOnAction(new EventHandler<ActionEvent>() {public void handle(ActionEvent e) {

            int month = d.getValue().getMonth().getValue();
            int day = d.getValue().getDayOfMonth();
            int year = d.getValue().getYear();

            transactionList = FXCollections.observableArrayList(transactionManager.viewTransaction(month, day, year));
            transactionTableController.setTransactionList(transactionList);
            
            s.close();
        }
        });
            
    }
    
    public void setTableController(TransactionTableController transactionTableController) {
        this.transactionTableController = transactionTableController;
    }
    
    public void setTransactionManager(DBTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}



