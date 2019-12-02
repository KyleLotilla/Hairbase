/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hairplay.hairbase.income.expense;
import java.math.BigDecimal;

/**
 *
 * @author MarcoMalabag
 */
public class AdditionalExpense implements Expense {
    
    private String name;
    private BigDecimal expense;

    public AdditionalExpense(String name, BigDecimal expense)
    {
        this.name = name;
        this.expense = expense;
    }

    public BigDecimal getTotalExpense()
    {
         return this.expense;
    }
    
    public String getName()
    {
        return name;
    }
}
