package com.hairplay.hairbase.income.report;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import com.hairplay.hairbase.income.MonthlyIncome;
import com.hairplay.hairbase.income.expense.AdditionalExpense;
import com.hairplay.hairbase.income.expense.InventoryExpense;
import com.hairplay.hairbase.income.expense.SalaryExpense;
import com.hairplay.hairbase.income.expense.ShareOfCompanyExpense;
import javafx.scene.control.Alert;

public class DetailedIncomeReportInputController {
    private MonthlyIncome monthlyIncome;
    private List<SalaryExpense> salaryExpenses;
    private List<ShareOfCompanyExpense> shareOfCompanyExpenses;
    private List<InventoryExpense> inventoryExpenses;
    private List<AdditionalExpense> additionalExpenses;

    public void generateDetailedMonthlyIncomeReport() throws IOException {
        BigDecimal computedMonthlyIncome = monthlyIncome.ComputeMonthly();
        LocalDate incomeTimeFrame = monthlyIncome.getTimeFrame();
        BigDecimal monthlySales = monthlyIncome.getMonthlySales();

        String fileName = incomeTimeFrame.format(DateTimeFormatter.ofPattern("MMMMYYYY")) + "DetailedMonthlyIncome" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".csv";

        FileWriter fileWriter = new FileWriter(fileName);

        fileWriter.append("Monthly Income of the Month " + incomeTimeFrame.format(DateTimeFormatter.ofPattern("MMMM YYYY"))  + ",\n\n");

        fileWriter.append("Monthly Sales,");
        fileWriter.append("Php " + monthlySales.toString() + ",\n");

        fileWriter.append("\nSalary,\n");
        fileWriter.append("Expense,Amount\n");
        
        Iterator<SalaryExpense> salaryIterator = salaryExpenses.iterator();
        BigDecimal totalSalary = new BigDecimal(0);
        
        while (salaryIterator.hasNext()) {
            SalaryExpense salary = salaryIterator.next();
            totalSalary = totalSalary.add(salary.getTotalExpense());

            fileWriter.append(salary.getEmployeeName() + " - " + "Total Salary Expense,");
            fileWriter.append("Php -" + salary.getTotalExpense() + ",\n");

            fileWriter.append(salary.getEmployeeName() + " - " + "Salary,");
            fileWriter.append("Php -" + salary.getSalary() + ",\n");

            fileWriter.append("SSS,");
            fileWriter.append("Php -" + salary.getSSSExpense() + ",\n");

            fileWriter.append("Pag-Ibig,");
            fileWriter.append("Php -" + salary.getPagIbigExpense() + ",\n");

            fileWriter.append("PhilHealth,");
            fileWriter.append("Php -" + salary.getPhilHealth() + ",\n");

            fileWriter.append("\n");
        }
        fileWriter.append("Total Salary Expense: ," + "Php -" + totalSalary.toString() + ",\n");

        fileWriter.append("\nShare of the Company,\n");
        fileWriter.append("Expense,Amount\n");
        Iterator<ShareOfCompanyExpense> shareofCompanyIterator = shareOfCompanyExpenses.iterator();
        BigDecimal totalSSS = new BigDecimal(0);
        BigDecimal totalPagIbig = new BigDecimal(0);
        BigDecimal totalPhilHealth = new BigDecimal(0);
        while (shareofCompanyIterator.hasNext()) {
            ShareOfCompanyExpense shareOfCompany = shareofCompanyIterator.next();
            totalSSS = totalSSS.add(shareOfCompany.getSSSExpense());
            totalPagIbig = totalPagIbig.add(shareOfCompany.getPagIbigExpense());
            totalPhilHealth = totalPhilHealth.add(shareOfCompany.getPhilHealth());
        }
        fileWriter.append("SSS," + "Php -" + totalSSS.toString() + ",\n");
        fileWriter.append("Pag-Ibig," + "Php -" + totalPagIbig.toString() + ",\n");
        fileWriter.append("Philhealth," + "Php -" + totalPhilHealth.toString() + ",\n");
        fileWriter.append("\nTotal Share of the Company Expenses:," + "Php -" + totalSSS.add(totalPagIbig).add(totalPhilHealth).toString() + ",\n");

        fileWriter.append("\nInventory,\n");
        fileWriter.append("Expense,Quantity,Total Cost\n");
        Iterator<InventoryExpense> inventoryIterator = inventoryExpenses.iterator();
        BigDecimal totalInventory = new BigDecimal(0);
        while(inventoryIterator.hasNext()) {
            InventoryExpense inventory = inventoryIterator.next();
            totalInventory = totalInventory.add(inventory.getTotalExpense());
            fileWriter.append(inventory.getInventoryName() + ",");
            fileWriter.append(inventory.getQuantity() + ",");
            fileWriter.append("Php -" + inventory.getTotalExpense() + ",\n");
        }
        fileWriter.append("\nTotal Inventory Expenses: ," + "Php -" + totalInventory.toString() + ",\n");
        
        fileWriter.append("\nAdditional Expenses,\n");
        fileWriter.append("Expense,Amount\n");
        Iterator<AdditionalExpense> additionalIterator = additionalExpenses.iterator();
        BigDecimal totalAdditionalCost = new BigDecimal(0);
        while (additionalIterator.hasNext()) {
            AdditionalExpense additional = additionalIterator.next();
            totalAdditionalCost = totalAdditionalCost.add(additional.getTotalExpense());
            fileWriter.append(additional.getName() + ",");
            fileWriter.append("Php -" + additional.getTotalExpense() + ",\n");
        }
        fileWriter.append("\nTotal Additional Expenses: ," + "Php -" + totalInventory.toString() + ",\n");

        fileWriter.append("\nMonthly Income:,Php " + computedMonthlyIncome.toString() + "\n");
        fileWriter.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GENERATION SUCCESSFUL!");
        alert.setHeaderText("You may open it using any text editor (MS Excel recommended)");
        alert.showAndWait();
    }

    public void setSalaryExpenses(List<SalaryExpense> salaryExpenses) {
        this.salaryExpenses = salaryExpenses;
    }

    public void setShareOfCompanyExpenses(List<ShareOfCompanyExpense> shareOfCompanyExpenses) {
        this.shareOfCompanyExpenses = shareOfCompanyExpenses;
    }

    public void setInventoryExpenses(List<InventoryExpense> inventoryExpenses) {
        this.inventoryExpenses = inventoryExpenses;
    }

    public void setAdditionalExpenses(List<AdditionalExpense> additionalExpenses) {
        this.additionalExpenses = additionalExpenses;
    }
    
    public void setMonthlyIncome(MonthlyIncome monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}