package com.hairplay.hairbase.income.sales;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class MonthlySalesTableController implements PropertyChangeListener {
    @FXML
    private TableColumn<BigDecimal, String> monthlySalesPlaceHolderCol;
    @FXML
    private TableColumn<BigDecimal, String> monthlySalesAmountCol;
    @FXML
    private TableView<BigDecimal> monthlySalesTable;

    public void initialize() {
        monthlySalesPlaceHolderCol.setCellValueFactory(new Callback<CellDataFeatures<BigDecimal, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<BigDecimal, String> c) {
                return new ReadOnlyStringWrapper("Monthly Sales");
            }
        });

        monthlySalesAmountCol.setCellValueFactory(new Callback<CellDataFeatures<BigDecimal, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<BigDecimal, String> c) {
                return new ReadOnlyStringWrapper(c.getValue().toString());
            }
        });

        monthlySalesTable.setSelectionModel(null);
        monthlySalesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        monthlySalesTable.setFixedCellSize(25);
        monthlySalesTable.prefHeightProperty().bind(new ReadOnlyDoubleWrapper(52));
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().contentEquals("monthlySales")) {
            ObservableList<BigDecimal> monthlySalesList = FXCollections.observableArrayList();
            if (evt.getNewValue() != null)
                monthlySalesList.add((BigDecimal) evt.getNewValue());
            else
                monthlySalesList.add(new BigDecimal(0));
            monthlySalesTable.setItems(monthlySalesList);
        }
    }
    

}