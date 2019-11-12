/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package income;
import income.Expense;
import java.math.BigDecimal;

/**
 *
 * @author MarcoMalabag
 */
public class RegularExpense implements Expense {
    
    private String name;
    private BigDecimal expense;

    public RegularExpense(String name, BigDecimal expense)
    {
        this.name = name;
        this.expense = expense;
    }

    public BigDecimal getExpense()
    {
         return this.expense;
    }
    
    public String getName()
    {
        return name;
    }
}
