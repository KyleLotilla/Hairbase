package com.hairplay.hairbase;

import java.io.IOException;
import java.net.URL;

import com.hairplay.hairbase.customerListing.CustomerListingViewController;
import com.hairplay.hairbase.income.IncomeViewController;
import com.hairplay.hairbase.transaction.DBTransactionManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainAppController {
    @FXML
    private BorderPane appBorderPane;
    private DBTransactionManager transactionManager;

    public MainAppController(DBTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void initialize() {
        try {
            showCustomerListing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCustomerListing() throws IOException {
        this.loadContentPane(getClass().getResource("customerListing/customerListingView.fxml"), new CustomerListingViewController(transactionManager));
    }

    public void showIncome() throws IOException {
        this.loadContentPane(getClass().getResource("income/incomeView.fxml"), new IncomeViewController(transactionManager));
    }

    private void loadContentPane(URL fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(fxml);
        loader.setController(controller);
        Pane contentPane = (Pane) loader.load();
        appBorderPane.setCenter(contentPane);
    }

} 