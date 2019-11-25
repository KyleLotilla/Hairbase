package com.hairplay.hairbase.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class MainTransactionController {
    @FXML
    private TransactionTableController transactionTableController;
    @FXML
    private AddTransactionButtonController addTransactionButtonController;
    private DBTransactionManager transactionManager;
    private ObservableList<Transaction> transactionList;

    public MainTransactionController(DBTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void initialize() {
        this.transactionList = FXCollections.observableArrayList(transactionManager.viewTransactions());
        transactionTableController.setTransactionList(this.transactionList);
        addTransactionButtonController.setTransactionManager(transactionManager);
        addTransactionButtonController.setTransactionList(this.transactionList);
    }
}