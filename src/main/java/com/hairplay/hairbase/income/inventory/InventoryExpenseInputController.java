package com.hairplay.hairbase.income.inventory;

import java.io.IOException;
import java.util.List;

import com.hairplay.hairbase.income.expense.InventoryExpense;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class InventoryExpenseInputController {
    private List<InventoryExpense> inventoryExpenses;
    
    public void openAddInventoryExpenseView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addInventoryExpenseView.fxml"));
        loader.setController(new AddInventoryExpenseViewController(inventoryExpenses));
        Pane addInventoryExpenseView = (Pane) loader.load();
        
        Stage addInventoryExpenseStage = new Stage();
        addInventoryExpenseStage.initModality(Modality.APPLICATION_MODAL);
        addInventoryExpenseStage.setTitle("Add New Inventory");
        addInventoryExpenseStage.setScene(new Scene(addInventoryExpenseView));
        addInventoryExpenseStage.show();
    }

    public void setInventoryExpenses(List<InventoryExpense> inventoryExpenses) {
        this.inventoryExpenses = inventoryExpenses;
    }
}