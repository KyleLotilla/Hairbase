package com.hairplay.hairbase.customerListing;

import com.hairplay.hairbase.transaction.DBTransactionManager;
import com.hairplay.hairbase.transaction.DBTransactionManager;
import com.hairplay.hairbase.transaction.Transaction;
import com.hairplay.hairbase.transaction.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTransactionViewController {
    @FXML TextField nameTextField;
    @FXML ComboBox<String> haircutTypeComboBox;
    @FXML CheckBox pwdCheckBox;
    @FXML Button submitTransactionButton;
    private DBTransactionManager transactionManager;
    private ObservableList<Transaction> transactionList;

    public AddTransactionViewController(DBTransactionManager transactionManager,ObservableList<Transaction> transactionList){
        this.transactionManager = transactionManager;
        this.transactionList = transactionList;
    }

    public void initialize() {}

    public void submitTransaction() {
        String name = nameTextField.getText();
        String haircutType = haircutTypeComboBox.getValue();
        boolean isPWD = pwdCheckBox.isSelected();
        BigDecimal amountPaid = null;
        Alert alert = new Alert(Alert.AlertType.NONE);

        if (haircutType.contentEquals("Child")) {
            if (isPWD)
                amountPaid = new BigDecimal(300.0);
            else
                amountPaid = new BigDecimal(350.0);
        }
        else if (haircutType.contentEquals("Child/Bangs")) {
            if (isPWD)
                amountPaid = new BigDecimal(150.0);
            else
                amountPaid = new BigDecimal(175.0);
        }
        else if (haircutType.contentEquals("Adult")) {
            if (isPWD)
                amountPaid = new BigDecimal(150.0);
            else
                amountPaid = new BigDecimal(170.0);
        }
        else if (haircutType.contentEquals("Senior")) {
            amountPaid = new BigDecimal(150.0);
        }

        if (isPWD && !haircutType.contentEquals("Senior"))
            haircutType = haircutType.concat("/PWD");
        
        if(name.isBlank()){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Customer name is blank");
            alert.setContentText("Please fill out customer name");
            alert.showAndWait();
        } else {
            Transaction generatedTransaction = new Transaction(LocalDate.now(), name, haircutType, amountPaid);
            transactionManager.addTransaction(generatedTransaction);
            transactionList.add(generatedTransaction);
            Stage window = (Stage) submitTransactionButton.getScene().getWindow();
            window.close();
        }
    }
}