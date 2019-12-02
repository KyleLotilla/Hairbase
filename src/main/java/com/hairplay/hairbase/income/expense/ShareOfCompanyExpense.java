package com.hairplay.hairbase.income.expense;

import java.math.BigDecimal;

public class ShareOfCompanyExpense implements Expense {
    private BigDecimal sssExpense;
    private BigDecimal pagIbigExpense;
    private BigDecimal philHealthExpense;
    private SalaryExpense referenceSalaryExpense;

    public ShareOfCompanyExpense(BigDecimal sssExpense, BigDecimal pagIbigExpense, BigDecimal philHealthExpense, SalaryExpense referenceSalaryExpense) {
        this.sssExpense = sssExpense;
        this.pagIbigExpense = pagIbigExpense;
        this.philHealthExpense = philHealthExpense;
        this.referenceSalaryExpense = referenceSalaryExpense;
    }

    public BigDecimal getSSSExpense() {
        return this.sssExpense;
    }

    public void setSSSExpense(BigDecimal sssExpense) {
        this.sssExpense = sssExpense;
    }

    public BigDecimal getPagIbigExpense() {
        return this.pagIbigExpense;
    }

    public void setPagIbigExpense(BigDecimal pagIbigExpense) {
        this.pagIbigExpense = pagIbigExpense;
    }

    public BigDecimal getPhilHealth() {
        return this.philHealthExpense;
    }

    public void setPhilHealthExpense(BigDecimal philHealthExpense) {
        this.philHealthExpense = philHealthExpense;
    }

    public SalaryExpense getReferenceSalaryExpense() {
        return referenceSalaryExpense;
    }

    public void setReferenceSalaryExpense(SalaryExpense referenceSalaryExpense) {
        this.referenceSalaryExpense = referenceSalaryExpense;
    }

    public BigDecimal getTotalExpense() {
        return sssExpense.add(pagIbigExpense).add(philHealthExpense);
    }
    
}