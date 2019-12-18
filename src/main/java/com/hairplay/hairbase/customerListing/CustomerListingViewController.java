package com.hairplay.hairbase.customerListing;

import com.hairplay.hairbase.transaction.DBTransactionManager;
import com.hairplay.hairbase.transaction.Transaction;
import com.hairplay.hairbase.customerListing.AddTransactionButtonController;
import com.hairplay.hairbase.transaction.DBTransactionManager;
import com.hairplay.hairbase.transaction.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class CustomerListingViewController {
    @FXML
    private TransactionTableController transactionTableController;
    @FXML
    private AddTransactionButtonController addTransactionButtonController;
    @FXML
    private FilterMonthTransactionButtonController filterMonthTransactionButtonController;
    @FXML
    private FilterDateTransactionButtonController filterDateTransactionButtonController;
    private DBTransactionManager transactionManager;
    private ObservableList<Transaction> transactionList;

    public CustomerListingViewController (DBTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void initialize() {
        this.transactionList = FXCollections.observableArrayList(this.transactionManager.viewTransactions());
        transactionTableController.setTransactionList(this.transactionList);
        addTransactionButtonController.setTransactionManager(this.transactionManager);
        addTransactionButtonController.setTransactionList(this.transactionList);
        filterMonthTransactionButtonController.setTableController(transactionTableController);
        filterMonthTransactionButtonController.setTransactionManager(transactionManager);
        filterDateTransactionButtonController.setTableController(transactionTableController);
        filterDateTransactionButtonController.setTransactionManager(transactionManager);
    }
}