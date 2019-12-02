package com.hairplay.hairbase.income.salary;

import com.hairplay.hairbase.income.expense.SalaryExpense;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AddSalaryExpenseViewController {
    @FXML
    private TextField employeeNameTextField;
    @FXML
    private TextField payrollTextField;
    @FXML
    private TextField numRegularWorkingDaysTextField;
    @FXML
    private TextField numWorkingHolidaysTextField;
    private List<SalaryExpense> salaryExpenses;

    public AddSalaryExpenseViewController(List<SalaryExpense> salaryExpenses) {
        this.salaryExpenses = salaryExpenses;
    }

    public void initialize() {
        payrollTextField.setTextFormatter(new TextFormatter<BigDecimal>(new BigDecimalStringConverter(), null, new UnaryOperator<Change>() {
            public Change apply(Change t) {
                String text = t.getText();
                if (!text.matches("\\d*(\\.\\d*)?"))
                    return null;
                else if (text.contains(".") && payrollTextField.getText().contains("."))
                    return null;
                else
                    return t;
            }
        }));

        numRegularWorkingDaysTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, new UnaryOperator<Change>() {
            public Change apply(Change t) {
                String text = t.getText();
                if (!text.matches("\\d*"))
                    return null;
                else
                    return t;
            }
        }));

        numWorkingHolidaysTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, new UnaryOperator<Change>() {
            public Change apply(Change t) {
                String text = t.getText();
                if (!text.matches("\\d*"))
                    return null;
                else
                    return t;
            }
        }));
    }

    public void addSalaryExpense() {
        SalaryExpense expense = new SalaryExpense();
        expense.setEmployeeName(employeeNameTextField.getText());
        expense.setPayroll((BigDecimal)payrollTextField.getTextFormatter().getValue());
        expense.setNumRegularWorkingDays((Integer)numRegularWorkingDaysTextField.getTextFormatter().getValue());
        expense.setNumWorkingHolidays((Integer)numWorkingHolidaysTextField.getTextFormatter().getValue());
        expense.setSSSExpense(expense.getPayroll());
        expense.setPagIbigExpense(new BigDecimal(100.0));
        expense.setPhilHealthExpense(new BigDecimal(100.0));
        salaryExpenses.add(expense);

        Stage window = (Stage) employeeNameTextField.getScene().getWindow();
        window.close();
    }

}