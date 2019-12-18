package com.hairplay.hairbase.income;

import com.hairplay.hairbase.income.expense.AdditionalExpense;
import com.hairplay.hairbase.income.expense.DeleteExpenseInputController;
import com.hairplay.hairbase.income.expense.InventoryExpense;
import com.hairplay.hairbase.income.expense.SalaryExpense;
import com.hairplay.hairbase.income.expense.ShareOfCompanyExpense;
import com.hairplay.hairbase.income.report.IncomeReportController;
import com.hairplay.hairbase.transaction.DBTransactionManager;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class IncomeViewController {
    @FXML
    private AccountTablesController accountTablesController;
    @FXML
    private IncomeInputsController incomeInputsController;
    @FXML
    private IncomeReportController incomeReportController;
    private ObservableList<SalaryExpense> salaryExpenses;
    private ObservableList<ShareOfCompanyExpense> shareOfCompanyExpenses;
    private ObservableList<InventoryExpense> inventoryExpenses;
    private ObservableList<AdditionalExpense> additionalExpenses;
    private MonthlyIncome monthlyIncome;

    public IncomeViewController(DBTransactionManager transactionManager) {
        this.monthlyIncome = new MonthlyIncome(transactionManager);
        this.salaryExpenses= FXCollections.observableArrayList();
        this.shareOfCompanyExpenses = FXCollections.observableArrayList();
        this.inventoryExpenses = FXCollections.observableArrayList();
        this.additionalExpenses = FXCollections.observableArrayList();

        monthlyIncome.addExpenses(salaryExpenses);
        monthlyIncome.addExpenses(shareOfCompanyExpenses);
        monthlyIncome.addExpenses(inventoryExpenses);
        monthlyIncome.addExpenses(additionalExpenses);

        listenSalaryExpenses();
    }

    public void initialize() {
        setDataModels();
        addExpenseSelectionModels();
        monthlyIncome.setTimeFrame(10, 2019);
    }

    private void setDataModels() {
        accountTablesController.setMonthlyIncome(monthlyIncome);
        accountTablesController.setSalaryExpenses(salaryExpenses);
        accountTablesController.setShareOfCompanyExpenses(shareOfCompanyExpenses);
        accountTablesController.setInventoryExpenses(inventoryExpenses);
        accountTablesController.setAdditionalExpense(additionalExpenses);
        incomeInputsController.setMonthlyIncome(monthlyIncome);
        incomeInputsController.setSalaryExpenses(salaryExpenses);
        incomeInputsController.setShareOfCompanyExpenses(shareOfCompanyExpenses);
        incomeInputsController.setInventoryExpenses(inventoryExpenses);
        incomeInputsController.setAdditionalExpense(additionalExpenses);
        monthlyIncome.addMonthlyIncomeListener(incomeReportController);
    }

    private void addExpenseSelectionModels() {
        DeleteExpenseInputController deleteExpenseInputController = incomeInputsController.getDeleteExpenseInputController();
        deleteExpenseInputController.addExpenseSelectionModel(accountTablesController.getSalaryExpenseTableController(), salaryExpenses);
        deleteExpenseInputController.addExpenseSelectionModel(accountTablesController.getInventoryExpenseTableController(), inventoryExpenses);
        deleteExpenseInputController.addExpenseSelectionModel(accountTablesController.getAdditionalExpenseTableController(), additionalExpenses);
    }

    private void listenSalaryExpenses() {
        salaryExpenses.addListener(new ListChangeListener<SalaryExpense>() {
            public void onChanged(Change<? extends SalaryExpense> c) {
                while(c.next()) {
                    for (SalaryExpense expense: c.getAddedSubList())
                        shareOfCompanyExpenses.add(new ShareOfCompanyExpense(expense.getSSSExpense(), expense.getPagIbigExpense(), expense.getPhilHealth(), expense));
                    for (SalaryExpense expense: c.getRemoved()) {
                        boolean foundExpense = false;
                        int size = shareOfCompanyExpenses.size();
                        for (int i = 0; i < size && !foundExpense; i++)
                            if (shareOfCompanyExpenses.get(i).getReferenceSalaryExpense() == expense) {
                                foundExpense = true;
                                shareOfCompanyExpenses.remove(i);
                            }
                    }
                }
            }
        });
    }
}