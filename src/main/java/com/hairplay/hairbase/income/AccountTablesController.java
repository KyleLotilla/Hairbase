package com.hairplay.hairbase.income;

import com.hairplay.hairbase.income.salary.SalaryExpenseTableController;
import com.hairplay.hairbase.income.additionalExpense.AdditionalExpenseTableController;
import com.hairplay.hairbase.income.expense.AdditionalExpense;
import com.hairplay.hairbase.income.expense.InventoryExpense;
import com.hairplay.hairbase.income.expense.SalaryExpense;
import com.hairplay.hairbase.income.expense.ShareOfCompanyExpense;
import com.hairplay.hairbase.income.inventory.InventoryExpenseTableController;
import com.hairplay.hairbase.income.sales.MonthlySalesTableController;
import com.hairplay.hairbase.income.shareOfCompany.ShareOfCompanyExpenseTableController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionModel;

public class AccountTablesController {
    @FXML
    private MonthlySalesTableController monthlySalesTableController;
    @FXML
    private SalaryExpenseTableController salaryExpenseTableController;
    @FXML
    private ShareOfCompanyExpenseTableController shareOfCompanyExpenseTableController;
    @FXML
    private InventoryExpenseTableController inventoryExpenseTableController;
    @FXML
    private AdditionalExpenseTableController additionalExpenseTableController;

    public void initialize() {
        SelectionModel<? extends Object> salarySelectionModel = salaryExpenseTableController.getTable().getSelectionModel();
        SelectionModel<? extends Object> inventorySelectionModel = inventoryExpenseTableController.getTable().getSelectionModel();
        SelectionModel<? extends Object> additionalExpenseSelectionModel = additionalExpenseTableController.getTable()
                .getSelectionModel();

        salarySelectionModel.selectedItemProperty().addListener(new ChangeListener<Object>() {
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    inventorySelectionModel.clearSelection();
                    additionalExpenseSelectionModel.clearSelection();
                }
            }
        });

        inventorySelectionModel.selectedItemProperty().addListener(new ChangeListener<Object>() {
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    salarySelectionModel.clearSelection();
                    additionalExpenseSelectionModel.clearSelection();
                }
            }
        });

        additionalExpenseSelectionModel.selectedItemProperty().addListener(new ChangeListener<Object>() {
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    inventorySelectionModel.clearSelection();
                    salarySelectionModel.clearSelection();
                }
            }
        });
        
    }

    public void setMonthlyIncome(MonthlyIncome monthlyIncome) {
        monthlyIncome.addMonthlySalesListener(monthlySalesTableController);
    }

    public void setSalaryExpenses(ObservableList<SalaryExpense> salaryExpenses) {
        salaryExpenses.addListener(salaryExpenseTableController);
    }

    public SalaryExpenseTableController getSalaryExpenseTableController() {
        return this.salaryExpenseTableController;
    }

    public void setShareOfCompanyExpenses(ObservableList<ShareOfCompanyExpense> shareOfCompanyExpenses) {
        shareOfCompanyExpenses.addListener(shareOfCompanyExpenseTableController);
    }

    public void setInventoryExpenses(ObservableList<InventoryExpense> inventoryExpenses) {
        inventoryExpenses.addListener(inventoryExpenseTableController);
    }

    public InventoryExpenseTableController getInventoryExpenseTableController() {
        return this.inventoryExpenseTableController;
    }

    public void setAdditionalExpense(ObservableList<AdditionalExpense> additionalExpenses) {
        additionalExpenses.addListener(additionalExpenseTableController);
    }

    public AdditionalExpenseTableController getAdditionalExpenseTableController() {
        return this.additionalExpenseTableController;
    }
}