package com.hairplay.hairbase.customerListing;

import com.hairplay.hairbase.transaction.DBTransactionManager;
import com.hairplay.hairbase.transaction.DBTransactionManager;
import com.hairplay.hairbase.transaction.Transaction;
import com.hairplay.hairbase.transaction.Transaction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddTransactionButtonController {
    @FXML 
    private Button addTransactionButton;
    private DBTransactionManager transactionManager;
    private ObservableList<Transaction> transactionList;

    public void initialize() {}

    public void openAddTransactionView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addTransactionView.fxml"));
        loader.setController(new AddTransactionViewController(transactionManager, transactionList));
        Pane addTransactionView = (Pane) loader.load();
        
        Stage addTransactionStage = new Stage();
        addTransactionStage.initModality(Modality.APPLICATION_MODAL);
        addTransactionStage.setTitle("Add New Transaction");
        addTransactionStage.setScene(new Scene(addTransactionView, 800, 500));
        addTransactionStage.show();
    }

    public void setTransactionManager(DBTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setTransactionList(ObservableList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}