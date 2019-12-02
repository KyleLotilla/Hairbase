package com.hairplay.hairbase.income.report;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class IncomeReportController implements PropertyChangeListener {
    @FXML
    Label monthlyIncomeText;

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().contentEquals("monthlyIncome")) {
            monthlyIncomeText.setText(((BigDecimal)evt.getNewValue()).toString());
        }
    }

}