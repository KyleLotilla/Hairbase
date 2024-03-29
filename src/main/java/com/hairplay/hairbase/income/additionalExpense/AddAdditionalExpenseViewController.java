package com.hairplay.hairbase.income.additionalExpense;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

import com.hairplay.hairbase.income.expense.AdditionalExpense;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;

public class AddAdditionalExpenseViewController {
    private List<AdditionalExpense> additionalExpenses;
    @FXML
    private TextField expenseNameTextField;
    @FXML
    private TextField amountTextField;

    public AddAdditionalExpenseViewController(List<AdditionalExpense> additionalExpenses) {
        this.additionalExpenses = additionalExpenses;
    }

    public void initialize() {
        amountTextField.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, new UnaryOperator<Change>() {
            public Change apply(Change t) {
                String text = t.getText();
                if (!text.matches("\\d*(\\.\\d*)?"))
                    return null;
                else if (text.contains(".") && amountTextField.getText().contains("."))
                    return null;
                else
                    return t;
            }
        }));
    }

    public void addAdditionalExpense() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(expenseNameTextField.getText().isBlank()){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Additional expense name is blank");
            alert.setContentText("Please fill out additional expense name");
            alert.showAndWait();
        } else if(amountTextField.getText().isBlank()){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Amount is blank");
            alert.setContentText("Please fill out amount");
            alert.showAndWait();            
        } else {
            additionalExpenses.add(new AdditionalExpense(expenseNameTextField.getText(), (BigDecimal)amountTextField.getTextFormatter().getValue()));

            Stage window = (Stage) expenseNameTextField.getScene().getWindow();
            window.close();
        }
    }
}