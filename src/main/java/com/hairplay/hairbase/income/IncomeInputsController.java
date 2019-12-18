package com.hairplay.hairbase.income;

import com.hairplay.hairbase.income.salary.SalaryExpenseInputController;
import com.hairplay.hairbase.income.additionalExpense.AdditionalExpenseInputController;
import com.hairplay.hairbase.income.expense.AdditionalExpense;
import com.hairplay.hairbase.income.expense.DeleteExpenseInputController;
import com.hairplay.hairbase.income.expense.InventoryExpense;
import com.hairplay.hairbase.income.expense.SalaryExpense;
import com.hairplay.hairbase.income.expense.ShareOfCompanyExpense;
import com.hairplay.hairbase.income.inventory.InventoryExpenseInputController;
import com.hairplay.hairbase.income.report.DetailedIncomeReportInputController;
import com.hairplay.hairbase.income.report.SummarizedIncomeReportInputController;
import com.hairplay.hairbase.income.sales.MonthlySalesInputController;
import java.util.List;

import javafx.fxml.FXML;

public class IncomeInputsController {
    @FXML
    private MonthlySalesInputController monthlySalesInputController;
    @FXML
    private SalaryExpenseInputController salaryExpenseInputController;
    @FXML
    private InventoryExpenseInputController inventoryExpenseInputController;
    @FXML
    private AdditionalExpenseInputController additionalExpenseInputController;
    @FXML
    private DeleteExpenseInputController deleteExpenseInputController;
    @FXML
    private ComputeMonthlyIncomeInputController computeMonthlyIncomeInputController;
    @FXML
    private SummarizedIncomeReportInputController summarizedIncomeReportInputController;
    @FXML
    private DetailedIncomeReportInputController detailedIncomeReportInputController;
    private MonthlyIncome monthlyIncome;

    public void initialize() {
        System.out.println("IncomeInputsController");
    }

    public void setMonthlyIncome(MonthlyIncome monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        monthlySalesInputController.setMonthlyIncome(monthlyIncome);
        computeMonthlyIncomeInputController.setMonthlyIncome(monthlyIncome);
        summarizedIncomeReportInputController.setMonthlyIncome(monthlyIncome);
        detailedIncomeReportInputController.setMonthlyIncome(monthlyIncome);
    }

    public void setSalaryExpenses(List<SalaryExpense> salaryExpenses) {
        salaryExpenseInputController.setSalaryExpenses(salaryExpenses);
        summarizedIncomeReportInputController.setSalaryExpenses(salaryExpenses);
        detailedIncomeReportInputController.setSalaryExpenses(salaryExpenses);
    }

    public void setShareOfCompanyExpenses(List<ShareOfCompanyExpense> shareOfCompanyExpenses) {
        summarizedIncomeReportInputController.setShareOfCompanyExpenses(shareOfCompanyExpenses);
        detailedIncomeReportInputController.setShareOfCompanyExpenses(shareOfCompanyExpenses);
    }

    public void setInventoryExpenses(List<InventoryExpense> inventoryExpenses) {
        inventoryExpenseInputController.setInventoryExpenses(inventoryExpenses);
        summarizedIncomeReportInputController.setInventoryExpenses(inventoryExpenses);
        detailedIncomeReportInputController.setInventoryExpenses(inventoryExpenses);
    }

    public void setAdditionalExpense(List<AdditionalExpense> additionalExpenses) {
        additionalExpenseInputController.setAdditionalExpenses(additionalExpenses);
        summarizedIncomeReportInputController.setAdditionalExpenses(additionalExpenses);
        detailedIncomeReportInputController.setAdditionalExpenses(additionalExpenses);
    }

    public DeleteExpenseInputController getDeleteExpenseInputController() {
        return this.deleteExpenseInputController;
    }
}