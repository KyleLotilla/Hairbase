package com.hairplay.hairbase.income.shareOfCompany;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.hairplay.hairbase.income.expense.ShareOfCompanyExpense;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.collections.ObservableList;

public class ShareOfCompanyExpenseTableController implements ListChangeListener<ShareOfCompanyExpense> {
    @FXML
    private TableView<ShareOfCompanyExpenseRow> shareOfCompanyExpenseTable;
    @FXML
    private TableColumn<ShareOfCompanyExpenseRow, String> shareOfCompanyExpenseDescCol;
    @FXML
    private TableColumn<ShareOfCompanyExpenseRow, String> shareOfCompanyExpenseAmountCol;

    public void initialize() {
        shareOfCompanyExpenseDescCol.setCellValueFactory(
                new Callback<CellDataFeatures<ShareOfCompanyExpenseRow, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ShareOfCompanyExpenseRow, String> c) {
                        return new ReadOnlyStringWrapper(c.getValue().getShareOfCompanyExpenseDesc());
                    }
                });

        shareOfCompanyExpenseAmountCol.setCellValueFactory(
                new Callback<CellDataFeatures<ShareOfCompanyExpenseRow, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ShareOfCompanyExpenseRow, String> c) {
                        return new ReadOnlyStringWrapper(c.getValue().getShareOfCompanyExpenseAmount().toString());
                    }
                });
        

        shareOfCompanyExpenseTable.setSelectionModel(null);
        shareOfCompanyExpenseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        shareOfCompanyExpenseTable.setFixedCellSize(25);
        shareOfCompanyExpenseTable.prefHeightProperty().bind(new ReadOnlyDoubleWrapper(110));
    }

    public void onChanged(Change<? extends ShareOfCompanyExpense> c) {
        BigDecimal sssExpense = new BigDecimal(0);
        BigDecimal pagIbigExpense = new BigDecimal(0);
        BigDecimal philHealthExpense = new BigDecimal(0);

        List<? extends ShareOfCompanyExpense> shareOfCompanyExpenses = c.getList();
        Iterator<? extends ShareOfCompanyExpense> iterator = shareOfCompanyExpenses.iterator();

        while(iterator.hasNext()) {
            ShareOfCompanyExpense index = iterator.next();
            sssExpense = sssExpense.add(index.getSSSExpense());
            pagIbigExpense = pagIbigExpense.add(index.getPagIbigExpense());
            philHealthExpense = philHealthExpense.add(index.getPhilHealth());
        }

        ObservableList<ShareOfCompanyExpenseRow> shareOfCompanyExpenseRows = FXCollections.observableArrayList();
        shareOfCompanyExpenseRows.add(new ShareOfCompanyExpenseRow("SSS", sssExpense));
        shareOfCompanyExpenseRows.add(new ShareOfCompanyExpenseRow("Pag-Ibig", pagIbigExpense));
        shareOfCompanyExpenseRows.add(new ShareOfCompanyExpenseRow("PhilHealth", philHealthExpense));

        shareOfCompanyExpenseTable.setItems(shareOfCompanyExpenseRows);
    }

    private class ShareOfCompanyExpenseRow {
        private String shareOfCompanyExpenseDesc;
        private BigDecimal shareOfCompanyExpenseAmount;

        public ShareOfCompanyExpenseRow(String shareOfCompanyExpenseDesc, BigDecimal shareOfCompanyExpenseAmount) {
            this.shareOfCompanyExpenseDesc = shareOfCompanyExpenseDesc;
            this.shareOfCompanyExpenseAmount = shareOfCompanyExpenseAmount;
        }

        public String getShareOfCompanyExpenseDesc() {
            return this.shareOfCompanyExpenseDesc;
        } 

        public BigDecimal getShareOfCompanyExpenseAmount() {
            return this.shareOfCompanyExpenseAmount;
        }
    }

    
}