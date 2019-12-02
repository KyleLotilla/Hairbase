package com.hairplay.hairbase.customerListing;

import com.hairplay.hairbase.transaction.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.fxml.FXML;

public class TransactionTableController {
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> transactionDateCol;
    @FXML
    private TableColumn<Transaction, String> customerNameCol;
    @FXML
    private TableColumn<Transaction, String> haircutTypeCol;
    @FXML
    private TableColumn<Transaction, String> amountPaidCol;

    public void initialize() {
        transactionDateCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> transaction) {
                LocalDate transactionDate = transaction.getValue().getDate();
                String formattedDate = transactionDate.format(DateTimeFormatter.ofPattern("MM-dd-YY"));
                return new ReadOnlyStringWrapper(formattedDate);
            }
        });

        customerNameCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> transaction) {
                return new ReadOnlyStringWrapper(transaction.getValue().getName());
            }
        });

        haircutTypeCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> transaction) {
                return new ReadOnlyStringWrapper(transaction.getValue().getHaircut());
            }
        });

        amountPaidCol.setCellValueFactory(new Callback<CellDataFeatures<Transaction, String>, ObservableValue<String>>(){
            public ObservableValue<String> call(CellDataFeatures<Transaction, String> transaction) {
                BigDecimal amountPaid = transaction.getValue().getAmount();
                return new ReadOnlyStringWrapper(amountPaid.toString());
            }
        });
    }

    public void setTransactionList(ObservableList<Transaction> transactionList) {
        transactionTable.setItems(transactionList);
    }
}