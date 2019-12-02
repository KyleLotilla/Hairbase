package com.hairplay.hairbase.income;

public class ComputeMonthlyIncomeInputController {
    private MonthlyIncome monthlyIncome;

    public void computeMonthlyIncome() {
        monthlyIncome.ComputeMonthly();
    }

    public void setMonthlyIncome(MonthlyIncome monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}