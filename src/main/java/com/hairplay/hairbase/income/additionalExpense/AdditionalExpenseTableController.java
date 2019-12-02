package com.hairplay.hairbase.income.additionalExpense;

import com.hairplay.hairbase.income.expense.AdditionalExpense;

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

public class AdditionalExpenseTableController implements ListChangeListener<AdditionalExpense>, ExpenseSelectionModel<AdditionalExpense> {
    @FXML
    private TableView<AdditionalExpense> additionalExpenseTable;
    @FXML
    private TableColumn<AdditionalExpense, String> additionalExpenseNameCol;
    @FXML
    private TableColumn<AdditionalExpense, String> additionalExpenseAmountCol;
    private ObservableList<AdditionalExpense> additionalExpenses;
    
    public void initialize() {
        additionalExpenseNameCol.setCellValueFactory(
            new Callback<CellDataFeatures<AdditionalExpense, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<AdditionalExpense, String> c) {
                    return new ReadOnlyStringWrapper(c.getValue().getName());
                    }
            });
         
        additionalExpenseAmountCol.setCellValueFactory(
            new Callback<CellDataFeatures<AdditionalExpense, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<AdditionalExpense, String> c) {
                   return new ReadOnlyStringWrapper(c.getValue().getTotalExpense().toString());
                   }
            });
        
        additionalExpenses = FXCollections.observableArrayList();
        additionalExpenseTable.setItems(additionalExpenses);
    }
    
    public AdditionalExpense getSelectedExpense() {
        return additionalExpenseTable.getSelectionModel().getSelectedItem();
    }

    
    public boolean isSelected() {
        if (additionalExpenseTable.getSelectionModel().getSelectedItem() != null)
            return true;
        else
            return false;
    }

    
    public void onChanged(Change<? extends AdditionalExpense> c) {
        while(c.next()) {
            for (AdditionalExpense expense: c.getAddedSubList())
                additionalExpenses.add(expense);
            for (AdditionalExpense expense: c.getRemoved())
                additionalExpenses.remove(expense);
        }
    }
    
    public TableView<AdditionalExpense> getTable() {
        return this.additionalExpenseTable;
    }
}