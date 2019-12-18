package com.hairplay.hairbase.income.salary;

import com.hairplay.hairbase.income.salary.AddSalaryExpenseViewController;
import com.hairplay.hairbase.income.expense.SalaryExpense;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SalaryExpenseInputController {
    private List<SalaryExpense> salaryExpenses;
    
    public void openAddSalaryExpenseView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addSalaryExpenseView.fxml"));
        loader.setController(new AddSalaryExpenseViewController(salaryExpenses));
        Pane addSalaryExpenseView = (Pane) loader.load();
        
        Stage addSalaryExpenseStage = new Stage();
        addSalaryExpenseStage.initModality(Modality.APPLICATION_MODAL);
        addSalaryExpenseStage.setTitle("Add New Salary");
        addSalaryExpenseStage.setScene(new Scene(addSalaryExpenseView));
        addSalaryExpenseStage.show();
    }

    public void setSalaryExpenses(List<SalaryExpense> salaryExpenses) {
        this.salaryExpenses = salaryExpenses;
    }
}