package com.hairplay.hairbase.income.salary;

import com.hairplay.hairbase.income.expense.SalaryExpense;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;
import com.hairplay.hairbase.income.expense.ExpenseSelectionModel;

public class SalaryExpenseTableController implements ExpenseSelectionModel<SalaryExpense>, ListChangeListener<SalaryExpense> {
    @FXML
    private TreeTableView<SalaryExpenseRow> salaryExpenseTable;
    @FXML
    private TreeTableColumn<SalaryExpenseRow, String> salaryExpenseDescCol;
    @FXML
    private TreeTableColumn<SalaryExpenseRow, String> salaryExpenseAmountCol;

    public void initialize() {
        salaryExpenseTable.setRoot(new TreeItem<SalaryExpenseRow>(new SalaryExpenseRow("", null, null)));
        salaryExpenseTable.setShowRoot(false);

        salaryExpenseDescCol.setCellValueFactory(
                new Callback<CellDataFeatures<SalaryExpenseRow, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<SalaryExpenseRow, String> c) {
                        return new ReadOnlyStringWrapper(c.getValue().getValue().getSalaryExpenseDesc());
                    }
                });

        salaryExpenseAmountCol.setCellValueFactory(
                new Callback<CellDataFeatures<SalaryExpenseRow, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<SalaryExpenseRow, String> c) {
                        return new ReadOnlyStringWrapper(c.getValue().getValue().getSalaryExpenseAmount().toString());
                    }
                });
    }

    public SalaryExpense getSelectedExpense() {
        if (salaryExpenseTable.getSelectionModel().getSelectedItem() != null)
            return salaryExpenseTable.getSelectionModel().getSelectedItem().getValue().getSalaryExpense();
        else
            return null;
    }

    
    public boolean isSelected() {
        if (salaryExpenseTable.getSelectionModel().getSelectedItem() != null)
            return true;
        else
            return false;
    }

    public void onChanged(Change<? extends SalaryExpense> changes) {
        while(changes.next()) {
            for(SalaryExpense expense: changes.getAddedSubList())
                addRow(expense);
            for(SalaryExpense expense: changes.getRemoved())
                removeRow(expense);
        }
    }

    private void addRow(SalaryExpense expense) {
        TreeItem<SalaryExpenseRow> salaryExpenseRow = new TreeItem<SalaryExpenseRow>(new SalaryExpenseRow(expense.getEmployeeName() + "- Total Salary Expense", expense.getTotalExpense(), expense));

        salaryExpenseRow.getChildren().add(new TreeItem<SalaryExpenseRow>(new SalaryExpenseRow(expense.getEmployeeName() + "- Salary", expense.getSalary(), expense)));

        salaryExpenseRow.getChildren().add(new TreeItem<SalaryExpenseRow>(new SalaryExpenseRow("SSS", expense.getSSSExpense(), expense)));

        salaryExpenseRow.getChildren().add(new TreeItem<SalaryExpenseRow>(new SalaryExpenseRow("Pag-ibig", expense.getPagIbigExpense(), expense)));

        salaryExpenseRow.getChildren().add(new TreeItem<SalaryExpenseRow>(new SalaryExpenseRow("Philhealth", expense.getPagIbigExpense(), expense)));

        salaryExpenseTable.getRoot().getChildren().add(salaryExpenseRow);
    }

    private void removeRow(SalaryExpense expense) {
        List<TreeItem<SalaryExpenseRow>> salaryExpenseRows = salaryExpenseTable.getRoot().getChildren();
        Iterator<TreeItem<SalaryExpenseRow>> iterator = salaryExpenseRows.iterator();
        boolean foundItem = false;
        TreeItem<SalaryExpenseRow> index = null;

        while(iterator.hasNext() && !foundItem) {
            index = iterator.next();
            if (index.getValue().getSalaryExpense() == expense) {
                foundItem = true;
            }
        }
        
        if (foundItem)
            salaryExpenseRows.remove(index);
    }

    public TreeTableView<SalaryExpenseRow> getTable() {
        return this.salaryExpenseTable;
    }

    private class SalaryExpenseRow {
        private String salaryExpenseDesc;
        private BigDecimal salaryExpenseAmount;
        private SalaryExpense referenceSalaryExpense;

        public SalaryExpenseRow(String salaryExpenseDesc, BigDecimal salaryExpenseAmount, SalaryExpense referenceSalaryExpense) {
            this.salaryExpenseDesc = salaryExpenseDesc;
            this.salaryExpenseAmount = salaryExpenseAmount;
            this.referenceSalaryExpense = referenceSalaryExpense;
        }

        public String getSalaryExpenseDesc() {
            return this.salaryExpenseDesc;
        } 

        public BigDecimal getSalaryExpenseAmount() {
            return this.salaryExpenseAmount;
        }

        public SalaryExpense getSalaryExpense() {
            return this.referenceSalaryExpense;
        }
    }

    
}