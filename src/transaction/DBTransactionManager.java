/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author user
 */
public interface DBTransactionManager {
    public List<Transaction> viewTransactions();
    public List<Transaction> viewTransactions(int month, int year);
    public List<Transaction> viewTransaction(int month, int day, int year);
    public void addTransaction(Transaction t);
    public BigDecimal getMonthlySales(int month, int year);
}
