package sofengg;

import sqlite.SQLiteConnect;
import transaction.Transaction;
import transaction.DBTransactionManager;
import transaction.SQLiteTransactionManager;
import java.math.*;
import java.sql.Connection;
import java.time.*;
import java.time.format.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import income.ExpenseList;
import income.Expense;
import income.RegularExpense;
import income.MonthlyIncome;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Driver {
    public static void main(String[] args) {
        int userInput = 0;
        Scanner scan = new Scanner(System.in);
        do {
            do {
                userInput = 0;
                System.out.println("Which function do you want to do?");
                System.out.println("[1] - View Transactions");
                System.out.println("[2] - Add Transactions");
                System.out.println("[3] - Compute Monthly Income");
                System.out.println("[4] - Exit");

                try {
                    userInput = scan.nextInt();
                    if (userInput <= 0 || userInput > 4)
                        System.out.println("\nInput must be a number from 1-4");
                } catch (InputMismatchException e) {
                    System.out.println("\nInput must be a number from 1-4");
                } finally {
                    scan.nextLine();
                }
            } while(userInput <= 0 || userInput > 4);
            
            switch(userInput){
                case 1: 
                    display();
                    break;
                case 2:
                    addTransaction();
                    break;
                case 3:
                    computeMonthlyIncome();
                    break;
            }
        } while (userInput != 4);
    }
    
    public static void display(){
        DBTransactionManager transManager = new SQLiteTransactionManager();             
        
        List<Transaction> tr = transManager.viewTransactions(); 
        
        for(int i=0; i< tr.size(); i++){
            System.out.print(tr.get(i).getDate() + " ");
            System.out.print(tr.get(i).getName() + " ");
            System.out.print(tr.get(i).getHaircut() + " ");
            System.out.println(tr.get(i).getAmount());
        }
    }
    
    public static void addTransaction() {   
        //gets input from the user for name and type of haircut and asks if customer is a PWD
        Scanner scan = new Scanner(System.in);
        DBTransactionManager transManager = new SQLiteTransactionManager();

        System.out.println("Enter Customer Name: ");
        String name = scan.nextLine();

        int haircut = 0;

        do {
            System.out.println("Enter Type of Haircut:\n[1] Child\n[2] Child/Bangs\n[3] Adult\n[4] Senior\n");
            try {
                haircut = scan.nextInt();
                if (haircut <= 0 || haircut > 4)
                    System.out.println("\nInput must be a number from 1-4");
            } catch (InputMismatchException e) {
                System.out.println("\nInput must be a number from 1-4");
            } finally {
                scan.nextLine();
            }
        } while (haircut <= 0 || haircut > 4);
        
        String isPWD = "N";
        if (haircut != 4) {
            do { 
                System.out.println("Is customer PWD?[Y/N]: ");
                isPWD = "";
                isPWD = scan.nextLine().toUpperCase();

                if (!isPWD.equals("Y") && !isPWD.equals("N")) {
                    System.out.println("\nInput must be Y or N");
                }
            } while (!isPWD.equals("Y") && !isPWD.equals("N"));
        }    
        BigDecimal amount = null;
        String haircutType = null;
        switch (haircut) {
            case 1:
                haircutType = "Child";
                break;
            case 2:
                haircutType = "Child/Bangs";
                break;
            case 3:
                haircutType = "Adult";
                break;
            case 4:
                haircutType = "Senior";
                break;
        }
        
        if (isPWD.equals("Y")) {
            switch (haircut) {
            case 1:
                amount = new BigDecimal(300.00);
                break;
            case 2:
                amount = new BigDecimal(150.00);
                break;
            case 3:
                amount = new BigDecimal(150.00);
                break;
            }
            haircutType = haircutType.concat("/PWD");
        }
        else {
            switch (haircut) {
                case 1:
                    amount = new BigDecimal(350.00);
                    break;
                case 2:
                    amount = new BigDecimal(175.00);
                    break;
                case 3:
                    amount = new BigDecimal(170.00);
                    break;
                case 4:
                    amount = new BigDecimal(150.00);
                    break;
            }
        }
        
        Transaction t = new Transaction(LocalDate.now(), name, haircutType, amount);
        transManager.addTransaction(t);
        System.out.println("New Transaction: ");
        System.out.print(t.getDate() + " ");
        System.out.print(t.getName() + " ");
        System.out.print(t.getHaircut() + " ");
        System.out.println(t.getAmount() + "\n");
    }
    
    public static void computeMonthlyIncome() {
        BigDecimal monthlySales = getMonthlySales();
        ArrayList<ExpenseList<? extends Expense>> expenses = getExpenses(monthlySales);
        MonthlyIncome income = new MonthlyIncome(monthlySales, expenses);
        System.out.println("Monthly Income: Php " + income.ComputeMonthly());
    }
    
    public static ArrayList<ExpenseList<? extends Expense>> getExpenses(BigDecimal monthlySales) {
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
        
        ArrayList<ExpenseList<? extends Expense>> expenses = new ArrayList<ExpenseList<? extends Expense>>();
        ExpenseList<RegularExpense> regExpenses = new ExpenseList<RegularExpense>();
        expenses.add(regExpenses);
        
        do {
            do {
                System.out.println();
                System.out.println("Monthly Sales: Php " + monthlySales);
                System.out.println("Expenses: ");
                viewExpenses(regExpenses);
                System.out.println();
                System.out.println("What operation would you like to do?\n[1] Add Expense [2] Remove Expense [3] Compute Monthly Income");
                
                try {
                    userInput = scan.nextInt();
                    if (userInput <= 0 || userInput > 3)
                        System.out.println("Input must be a number between 1-3");
                } catch (InputMismatchException e) {
                    System.out.println("Input must be a number between 1-3");
                } finally {
                    scan.nextLine();
                }
            } while (userInput <= 0 || userInput > 3);
            
            switch(userInput) {
                case 1:
                    regExpenses.addExpense(addExpense());
                    break;
                case 2:
                    System.out.println();
                    if (regExpenses.getSize() <= 0)
                        System.out.println("\nThere is no Expenses added yet to delete");
                    else
                        removeExpense(regExpenses);
                    break;
                case 3:
                    if (regExpenses.getSize() == 0) {
                        System.out.println("\nThere is no Expenses added yet");
                        userInput = 0;
                    }
            }
        } while (userInput != 3);
        
        return expenses;
    }
    
    public static RegularExpense addExpense() {
        Scanner scan = new Scanner(System.in);
        String name = null;
        double amount = -1.0;
        
        System.out.println("What is the Name of the Expense: ");
        name = scan.nextLine();
        
        do {
            try {
                System.out.println("What is the Amount of the Expense: ");
                amount = scan.nextDouble();
                if (amount <= 0.0)
                    System.out.println("\nAmount must be a positive number");
            } catch (InputMismatchException e) {
                System.out.println("\nAmount must be a positive number");
            } finally {
                scan.nextLine();
            }
        } while (amount <= 0.0);
        
        return new RegularExpense(name, new BigDecimal(amount));
    }
    
    public static void removeExpense(ExpenseList<RegularExpense> regExpenses) {
        Scanner scan = new Scanner(System.in);
        int size = regExpenses.getSize();
        int delIndex = -1;
        
        do {
            try {
                System.out.println("Expenses: ");
                viewExpensesIndex(regExpenses);
                System.out.println();
                System.out.println("What Expense Number to Remove? ");
                delIndex = scan.nextInt();
                if (delIndex <= 0 || delIndex > size)
                    System.out.println("\nInput must a number between 1-" + size);
            } catch(InputMismatchException e) {
                System.out.println("\nInput must a number between 1-" + size);
            } finally {
                scan.nextLine();
            }
        } while (delIndex <= 0 || delIndex > size);
        
        regExpenses.removeExpense(delIndex - 1);
    }
    
    public static BigDecimal getMonthlySales() {
        int month = 0;
        int year = 0;
        Scanner scan = new Scanner(System.in);
        DBTransactionManager transManager = new SQLiteTransactionManager();
        
        do {
            try {
                System.out.println("What Month[1-12]?");
                month = scan.nextInt();
                if (month <= 0 || month > 12)
                    System.out.println("\nInput must be a number from 1-12");
            } catch (InputMismatchException e) {
                System.out.println("\nInput must be a number from 1-12");
            } finally {
                scan.nextLine();
            }
        } while (month <= 0 || month > 12);
        
        do {
            try {
                System.out.println("What Year?");
                year = scan.nextInt();
                if (year <= 999 || year > 9999)
                    System.out.println("\nInput must be a year YYYY");
            } catch (InputMismatchException e) {
                System.out.println("\nInput must be a year YYYY");
            } finally {
                scan.nextLine();
            }
        } while (year <= 999 || year > 9999);
        
        BigDecimal monthlySales = transManager.getMonthlySales(month, year);
        return monthlySales;
    } 
    
    public static void viewExpenses(ExpenseList<RegularExpense> expenses) {
        if (expenses.getSize() == 0)
            System.out.println("None");
        else {
            for (int i = 0; i < expenses.getSize(); i++) {
                RegularExpense expense = expenses.getExpense(i);
                System.out.println(expense.getName() + " - Php " + expense.getExpense());
            }
        }
    }
    
    public static void viewExpensesIndex(ExpenseList<RegularExpense> expenses) {
        for (int i = 0; i < expenses.getSize(); i++) {
            RegularExpense expense = expenses.getExpense(i);
            System.out.println("[" + (i + 1) + "]" + expense.getName() + " - Php " + expense.getExpense());
        }
    }

}
