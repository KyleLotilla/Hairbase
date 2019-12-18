package com.hairplay.hairbase.income.additionalExpense;

import java.io.IOException;
import java.util.List;

import com.hairplay.hairbase.income.expense.AdditionalExpense;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdditionalExpenseInputController {
    private List<AdditionalExpense> additionalExpenses;
    
    public void openAddAdditionalExpenseView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addAdditionalExpenseView.fxml"));
        loader.setController(new AddAdditionalExpenseViewController(additionalExpenses));
        Pane addAdditionalExpenseView = (Pane) loader.load();
        
        Stage addAdditionalExpenseStage = new Stage();
        addAdditionalExpenseStage.initModality(Modality.APPLICATION_MODAL);
        addAdditionalExpenseStage.setTitle("Add New Additional Expense");
        addAdditionalExpenseStage.setScene(new Scene(addAdditionalExpenseView));
        addAdditionalExpenseStage.show();
    }

    public void setAdditionalExpenses(List<AdditionalExpense> additionalExpenses) {
        this.additionalExpenses = additionalExpenses;
    }
}