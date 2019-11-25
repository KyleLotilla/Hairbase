/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Transaction {
    private LocalDate date;
    private String name;
    private String haircut;
    private BigDecimal amount;
    
    public Transaction(LocalDate date, String name, String haircut, BigDecimal amount){
        this.date = date;
        this.name = name;
        this.haircut = haircut;
        this.amount = amount;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }
    public LocalDate getDate(){
        return date;
    }
    
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    
    public void setHaircut(String haircut){
        this.haircut = haircut;
    }
    public String getHaircut(){
        return haircut;
    }
    
}
