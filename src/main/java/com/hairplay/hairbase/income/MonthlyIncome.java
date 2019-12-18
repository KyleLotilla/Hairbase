/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hairplay.hairbase.income;

import com.hairplay.hairbase.income.expense.Expense;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hairplay.hairbase.transaction.DBTransactionManager;

public class MonthlyIncome {
    
    private BigDecimal monthlySales;
    private BigDecimal monthlyIncome;
    private List<List<? extends Expense>> expenses;
    private LocalDate incomeTimeFrame;
    private PropertyChangeSupport monthlySalesListeners;
    private PropertyChangeSupport timeFrameListeners;
    private PropertyChangeSupport monthlyIncomeListeners;
    private DBTransactionManager transactionManager;
    
    public MonthlyIncome(DBTransactionManager transactionManager)
    {
        this.transactionManager = transactionManager;
        expenses = new ArrayList<List<? extends Expense>>();
        monthlySalesListeners = new PropertyChangeSupport(this);
        timeFrameListeners = new PropertyChangeSupport(this);
        monthlyIncomeListeners = new PropertyChangeSupport(this);
    }

    public void setTimeFrame(int month, int year) {
        LocalDate newTimeFrame = LocalDate.of(year, month, 1);
        BigDecimal newMonthlySales = transactionManager.getMonthlySales(month, year);
        if (newMonthlySales == null)
            newMonthlySales = new BigDecimal(0);
        timeFrameListeners.firePropertyChange("incomeTimeFrame", this.incomeTimeFrame, newTimeFrame);
        monthlySalesListeners.firePropertyChange("monthlySales", this.monthlySales, newMonthlySales);
        this.incomeTimeFrame = newTimeFrame;
        this.monthlySales = newMonthlySales;
    }

    public void addExpenses(List<? extends Expense> expenses) {
        this.expenses.add(expenses);
    }

    public BigDecimal ComputeMonthly()
    {
        BigDecimal income;
        income = this.monthlySales;
        Iterator<List<? extends Expense>> iterator = expenses.iterator();

        while(iterator.hasNext()) {
            Iterator<? extends Expense> subIterator = iterator.next().iterator();
            while(subIterator.hasNext())
                income = income.subtract(subIterator.next().getTotalExpense());
        }

        monthlyIncomeListeners.firePropertyChange("monthlyIncome", this.monthlyIncome, income);
        this.monthlyIncome = income;

        return this.monthlyIncome;
    }

    public void addMonthlySalesListener(PropertyChangeListener listener) {
        System.out.println(listener);
        monthlySalesListeners.addPropertyChangeListener(listener);
    }
    
    public void addTimeFrameListener(PropertyChangeListener listener) {
        timeFrameListeners.addPropertyChangeListener(listener);
    }

    public void addMonthlyIncomeListener(PropertyChangeListener listener) {
        monthlyIncomeListeners.addPropertyChangeListener(listener);
    }

    public LocalDate getTimeFrame() {
        return this.incomeTimeFrame;
    }

    public BigDecimal getMonthlySales() {
        return this.monthlySales;
    }
}
