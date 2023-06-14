package com.example.marisa.controller;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.view.WindowLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static com.example.marisa.main.Main.ucSaleList;

public class SaleManagmentController {
    public TableColumn<Sale, String> cId;
    public TableColumn<Sale, String> cCashierId;
    public TableColumn<Sale, String> cCustomerCPF;
    public TableColumn<Sale, String> cTotalValue;
    public TableColumn<Sale, String> cDiscount;
    public TableColumn<Sale, String> cCreationDate;
    public TableView<Sale> tableView;
    private ObservableList<Sale> tableData;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCashierId.setCellValueFactory(new PropertyValueFactory<>("cashierID"));
        cCustomerCPF.setCellValueFactory(new PropertyValueFactory<>("customerCPF"));
        cTotalValue.setCellValueFactory(new PropertyValueFactory<>("totalPayablePrice"));
        cDiscount.setCellValueFactory(new PropertyValueFactory<>("totalDiscount"));
        cCreationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadDataAndShow() {
        List<Sale> saleList = ucSaleList.listSales();
        tableData.clear();
        tableData.addAll(saleList);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLMain");
    }
}
