package com.hairplay.hairbase.income.inventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

import com.hairplay.hairbase.income.expense.InventoryExpense;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AddInventoryExpenseViewController {
    private List<InventoryExpense> inventoryExpenses;
    @FXML
    private TextField inventoryNameTextField;
    @FXML
    private TextField baseCostOfInventoryTextField;
    @FXML
    private TextField quantityTextField;

    public AddInventoryExpenseViewController(List<InventoryExpense> inventoryExpenses) {
        this.inventoryExpenses = inventoryExpenses;
    }

    public void initialize() {
        baseCostOfInventoryTextField.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, new UnaryOperator<Change>() {
            public Change apply(Change t) {
                String text = t.getText();
                if (!text.matches("\\d*(\\.\\d*)?"))
                    return null;
                else if (text.contains(".") && baseCostOfInventoryTextField.getText().contains("."))
                    return null;
                else
                    return t;
            }
        }));

        quantityTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, new UnaryOperator<Change>() {
            public Change apply(Change t) {
                String text = t.getText();
                if (!text.matches("\\d*"))
                    return null;
                else
                    return t;
            }
        }));
    }

    public void addInventoryExpense() {
        Alert alert = new Alert(AlertType.NONE);
        if(inventoryNameTextField.getText().isBlank()){
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Inventory name is blank");
            alert.setContentText("Please fill out inventory name");
            alert.showAndWait();
        } else if(baseCostOfInventoryTextField.getText().isBlank()) {
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Cost per single quantity is blank");
            alert.setContentText("Please fill out cost per single quantity");
            alert.showAndWait();            
        } else if(quantityTextField.getText().isBlank()) {
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Quantity is blank");
            alert.setContentText("Please fill out quantity");
            alert.showAndWait();
        } else {
            inventoryExpenses.add(new InventoryExpense(inventoryNameTextField.getText(), (BigDecimal)baseCostOfInventoryTextField.getTextFormatter().getValue(), (Integer)quantityTextField.getTextFormatter().getValue()));
           
            Stage window = (Stage) inventoryNameTextField.getScene().getWindow();
            window.close();
        }
    }
}