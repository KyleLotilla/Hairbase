/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package income;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author MarcoMalabag
 */
public class MonthlyIncome {
    
    private BigDecimal monthlySales;
    private ArrayList<ExpenseList<? extends Expense>> expenses;
    
    public MonthlyIncome(BigDecimal monthlySales, ArrayList<ExpenseList<? extends Expense>> expenses)
    {
        this.expenses = expenses;
        this.monthlySales = monthlySales;
    }
    
    public BigDecimal ComputeMonthly()
    {
        BigDecimal income;
        income = this.monthlySales;
        
        for(int i = 0; i < expenses.size(); i++)
            income = income.subtract(expenses.get(i).getTotalExpense());
        
        return income;
    }
}
