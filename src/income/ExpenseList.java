/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package income;
import income.Expense;
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
public class ExpenseList<T extends Expense> {
    
    private ArrayList<T> expenses;
    
    public ExpenseList(){
        expenses = new ArrayList<T>();
    }
    
    public void addExpense(T expense)
    {
        this.expenses.add(expense);
    }
    
    public int getSize()
    {
        return this.expenses.size();
    }
    
    public void removeExpense(int index)
    {
        expenses.remove(index);
    }
    
    public T getExpense(int index)
    {
        return expenses.get(index);
    }
    
    public BigDecimal getTotalExpense() 
    {
        BigDecimal total = new BigDecimal(0.0);
        for (Expense e: expenses) 
        {
            total = total.add(e.getExpense());
        }
        return total;
    }
}
