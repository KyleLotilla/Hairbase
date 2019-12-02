package com.hairplay.hairbase.income.expense;

import java.math.BigDecimal;

public class InventoryExpense implements Expense {
    private String inventoryName;
    private BigDecimal baseCostOfInventory;
    private int quantity;

    public InventoryExpense (String inventoryName, BigDecimal baseCostOfInventory, int quantity) {
        this.inventoryName = inventoryName;
        this.baseCostOfInventory = baseCostOfInventory;
        this.quantity = quantity;
    }

    public String getInventoryName() {
        return this.inventoryName;
    }

    public BigDecimal getBaseCostOfInventory() {
        return this.baseCostOfInventory;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public BigDecimal getTotalExpense() {
        return baseCostOfInventory.multiply(new BigDecimal(quantity));
    }
    
}