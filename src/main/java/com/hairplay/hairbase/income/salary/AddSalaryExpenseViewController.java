package com.hairplay.hairbase.income.salary;

import com.hairplay.hairbase.income.expense.SalaryExpense;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(employeeNameTextField.getText().isBlank()){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Employee name is blank");
            alert.setContentText("Please fill out employee name");
            alert.showAndWait();
        } else if(payrollTextField.getText().isBlank()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Payroll is blank");
            alert.setContentText("Please fill out payroll");
            alert.showAndWait();
        } else if(numRegularWorkingDaysTextField.getText().isBlank()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Number of regular working days is blank");
            alert.setContentText("Please fill out number of regular working days");
            alert.showAndWait();
        } else if(numWorkingHolidaysTextField.getText().isBlank()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Number of working holidays is blank");
            alert.setContentText("Please fill out number of working holidays");
            alert.showAndWait();
        } else { 
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

}