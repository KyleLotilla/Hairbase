package com.hairplay.hairbase.income.sales;

import com.hairplay.hairbase.income.MonthlyIncome;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.IntegerStringConverter;

public class MonthlySalesInputController {
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private TextField yearTextField;
    private MonthlyIncome monthlyIncome;

    public void initialize() {
        monthComboBox.getSelectionModel().select(0);

        yearTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, new UnaryOperator<Change>() {
                    public Change apply(Change t) {
                        String text = t.getText();
                        if (!text.matches("\\d*"))
                            return null;
                        else
                            return t;
                    }
        }));
    }

    public void setMonthlyIncome(MonthlyIncome monthlyIncome) {
        this.monthlyIncome = monthlyIncome;   
    }

    public void changeTimeFrame() {
        int month = monthComboBox.getSelectionModel().getSelectedIndex();
        int year = Integer.parseInt(yearTextField.getText());
        monthlyIncome.setTimeFrame(month + 1, year);
    }
}