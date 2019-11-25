package com.hairplay.hairbase.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

        Transaction generatedTransaction = new Transaction(LocalDate.now(), name, haircutType, amountPaid);
        transactionManager.addTransaction(generatedTransaction);
        transactionList.add(generatedTransaction);
        
        Stage window = (Stage) submitTransactionButton.getScene().getWindow();
        window.close();
    }
}