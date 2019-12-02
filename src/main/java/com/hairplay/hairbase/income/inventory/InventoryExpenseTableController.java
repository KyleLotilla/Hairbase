package com.hairplay.hairbase.income.inventory;


import com.hairplay.hairbase.income.expense.InventoryExpense;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import com.hairplay.hairbase.income.expense.ExpenseSelectionModel;

public class InventoryExpenseTableController implements ListChangeListener<InventoryExpense>, ExpenseSelectionModel<InventoryExpense> {
    @FXML
    private TableView<InventoryExpense> inventoryExpenseTable;
    @FXML
    private TableColumn<InventoryExpense, String> inventoryExpenseNameCol;
    @FXML
    private TableColumn<InventoryExpense, Integer> inventoryExpenseQuantityCol;
    @FXML
    private TableColumn<InventoryExpense, String> inventoryExpenseTotalCostCol;
    @FXML
    private ObservableList<InventoryExpense> inventoryExpenses;

    public void initialize() {
        inventoryExpenseNameCol.setCellValueFactory(
                new Callback<CellDataFeatures<InventoryExpense, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<InventoryExpense, String> c) {
                        return new ReadOnlyStringWrapper(c.getValue().getInventoryName());
                    }
                });
        
        inventoryExpenseQuantityCol.setCellValueFactory(
                new Callback<CellDataFeatures<InventoryExpense, Integer>, ObservableValue<Integer>>() {
                    public ObservableValue<Integer> call(CellDataFeatures<InventoryExpense, Integer> c) {
                        return new ReadOnlyObjectWrapper<Integer>(c.getValue().getQuantity());
                    }
                });

        inventoryExpenseTotalCostCol.setCellValueFactory(
                new Callback<CellDataFeatures<InventoryExpense, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<InventoryExpense, String> c) {
                        return new ReadOnlyStringWrapper(c.getValue().getTotalExpense().toString());
                    }
                });
        inventoryExpenses = FXCollections.observableArrayList();
        inventoryExpenseTable.setItems(inventoryExpenses);
    }

    public InventoryExpense getSelectedExpense() {
        return inventoryExpenseTable.getSelectionModel().getSelectedItem();
    }


    public boolean isSelected() {
        if (inventoryExpenseTable.getSelectionModel().getSelectedItem() != null)
            return true;
        else
            return false;
    }


    public void onChanged(Change<? extends InventoryExpense> c) {
        while(c.next()) {
            for (InventoryExpense expense: c.getAddedSubList())
                inventoryExpenses.add(expense);
            for (InventoryExpense expense: c.getRemoved())
                inventoryExpenses.remove(expense);
        }
    }

    public TableView<InventoryExpense> getTable() {
        return this.inventoryExpenseTable;
    }

}