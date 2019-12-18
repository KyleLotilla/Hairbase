package com.hairplay.hairbase.income.report;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import com.hairplay.hairbase.income.MonthlyIncome;
import com.hairplay.hairbase.income.expense.AdditionalExpense;
import com.hairplay.hairbase.income.expense.InventoryExpense;
import com.hairplay.hairbase.income.expense.SalaryExpense;
import com.hairplay.hairbase.income.expense.ShareOfCompanyExpense;
import java.time.LocalDateTime;
import javafx.scene.control.Alert;

public class SummarizedIncomeReportInputController {
    private MonthlyIncome monthlyIncome;
    private List<SalaryExpense> salaryExpenses;
    private List<ShareOfCompanyExpense> shareOfCompanyExpenses;
    private List<InventoryExpense> inventoryExpenses;
    private List<AdditionalExpense> additionalExpenses;

    public void generateSummarizedMonthlyIncomeReport() throws IOException {
        BigDecimal computedMonthlyIncome = monthlyIncome.ComputeMonthly();
        LocalDate incomeTimeFrame = monthlyIncome.getTimeFrame();
        BigDecimal monthlySales = monthlyIncome.getMonthlySales();

        String fileName = incomeTimeFrame.format(DateTimeFormatter.ofPattern("MMMMYYYY")) + "SummarizedMonthlyIncome" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".csv";

        FileWriter fileWriter = new FileWriter(fileName);

        fileWriter.append("Monthly Income of the Month " + incomeTimeFrame.format(DateTimeFormatter.ofPattern("MMMM YYYY"))  + ",\n\n");

        fileWriter.append("Monthly Sales,");
        fileWriter.append("Php " + monthlySales.toString() + ",");

        fileWriter.append("\n\n");
        fileWriter.append("Expenses, Amount,\n");

        Iterator<SalaryExpense> salaryIterator = salaryExpenses.iterator();
        BigDecimal totalSalary = new BigDecimal(0);
        BigDecimal totalSSS = new BigDecimal(0);
        BigDecimal totalPagIbig = new BigDecimal(0);
        BigDecimal totalPhilHealth = new BigDecimal(0);
        while (salaryIterator.hasNext()) {
            SalaryExpense salary = salaryIterator.next();
            totalSalary = totalSalary.add(salary.getSalary());
            totalSSS = totalSSS.add(salary.getSSSExpense());
            totalPagIbig = totalPagIbig.add(salary.getPagIbigExpense());
            totalPhilHealth = totalPhilHealth.add(salary.getPhilHealth());
        }
        fileWriter.append("\"Total Salary (w/o SSS, Pag-Ibig, & Philhealth)\"," + "Php -" + totalSalary.toString() + ",\n");

        Iterator<ShareOfCompanyExpense> shareofCompanyIterator = shareOfCompanyExpenses.iterator();
        while (shareofCompanyIterator.hasNext()) {
            ShareOfCompanyExpense shareOfCompany = shareofCompanyIterator.next();
            totalSSS = totalSSS.add(shareOfCompany.getSSSExpense());
            totalPagIbig = totalPagIbig.add(shareOfCompany.getPagIbigExpense());
            totalPhilHealth = totalPhilHealth.add(shareOfCompany.getPhilHealth());
        }
        fileWriter.append("Total SSS (From Salary & Company)," + "Php -" + totalSSS.toString() + ",\n");
        fileWriter.append("Total Pag-Ibig (From Salary & Company)," + "Php -" + totalPagIbig.toString() + ",\n");
        fileWriter.append("Total Philhealth (From Salary & Company)," + "Php -" + totalPhilHealth.toString() + ",\n");

        Iterator<InventoryExpense> inventoryIterator = inventoryExpenses.iterator();
        while(inventoryIterator.hasNext()) {
            InventoryExpense inventory = inventoryIterator.next();
            fileWriter.append(inventory.getInventoryName() + "(x" + inventory.getQuantity() + ")" + ",");
            fileWriter.append("Php -" + inventory.getTotalExpense() + ",\n");
        }

        Iterator<AdditionalExpense> additionalIterator = additionalExpenses.iterator();
        while (additionalIterator.hasNext()) {
            AdditionalExpense additional = additionalIterator.next();
            fileWriter.append(additional.getName() + ",");
            fileWriter.append("Php -" + additional.getTotalExpense() + ",\n");
        }

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