package com.hairplay.hairbase.income.expense;

public interface ExpenseSelectionModel<T extends Expense> {
    public T getSelectedExpense();
    public boolean isSelected();
}