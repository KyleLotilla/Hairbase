package com.hairplay.hairbase.income.expense;

import java.util.ArrayList;
import java.util.List;

public class DeleteExpenseInputController {
    private List<ExpenseSelectionModel<?>> selectionModels;
    private List<List<? extends Expense>> expenses;

    public DeleteExpenseInputController() {
        selectionModels = new ArrayList<ExpenseSelectionModel<?>>();
        expenses = new ArrayList<List<? extends Expense>>();
    }

    public void deleteExpense() {
        for (int i = 0; i < selectionModels.size(); i++) {
            Expense selectedExpense = selectionModels.get(i).getSelectedExpense();
            if (selectedExpense != null)
                expenses.get(i).remove(selectedExpense);
        }
    }

    public void addExpenseSelectionModel(ExpenseSelectionModel<?> selectionModel, List<? extends Expense> expenseDataModel) {
        selectionModels.add(selectionModel);
        expenses.add(expenseDataModel);
    }
} 