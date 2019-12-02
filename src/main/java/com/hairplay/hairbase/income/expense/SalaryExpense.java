package com.hairplay.hairbase.income.expense;

import java.math.BigDecimal;

public class SalaryExpense implements Expense {
    private BigDecimal payroll;
    private BigDecimal sssExpense;
    private BigDecimal pagIbigExpense;
    private BigDecimal philHealthExpense;
    private String employeeName;
    private int numRegularWorkingDays;
    private int numWorkingHolidays;

    public BigDecimal getPayroll() {
        return this.payroll;
    }

    public void setPayroll(BigDecimal payroll) {
        this.payroll = payroll;
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

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setNumRegularWorkingDays(int numRegularWorkingDays) {
        this.numRegularWorkingDays = numRegularWorkingDays;
    }

    public void setNumWorkingHolidays(int numWorkingHolidays) {
        this.numWorkingHolidays = numWorkingHolidays;
    }

    public BigDecimal getSalary() {
        BigDecimal salary = payroll.multiply(new BigDecimal(numRegularWorkingDays));
        salary = salary.add(payroll.multiply(new BigDecimal(2 * numWorkingHolidays)));
        salary = salary.subtract(sssExpense);
        salary = salary.subtract(pagIbigExpense);
        salary = salary.subtract(philHealthExpense);
        return salary;
    }

    public BigDecimal getTotalExpense() {
        BigDecimal totalExpense = getSalary();
        totalExpense = totalExpense.add(sssExpense);
        totalExpense = totalExpense.add(pagIbigExpense);
        totalExpense = totalExpense.add(philHealthExpense);
        return totalExpense;
    }

}