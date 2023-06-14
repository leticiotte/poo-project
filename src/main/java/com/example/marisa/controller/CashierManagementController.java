package com.example.marisa.controller;

import static com.example.marisa.main.Main.*;
import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.view.WindowLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class CashierManagementController {
    @FXML
    public TableView<Cashier> tableView;

    @FXML
    public TableColumn<Cashier, Integer> cId;
    @FXML
    public TableColumn<Cashier, Double> cOpeningBalance;
    @FXML
    public TableColumn<Cashier, Double> cFinalBalance;
    @FXML
    public TableColumn<Cashier, CashierStatusEnum> cStatus;
    public TextField inpSearch;

    private ObservableList<Cashier> tableData;

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
        cOpeningBalance.setCellValueFactory(new PropertyValueFactory<>("openingBalance"));
        cFinalBalance.setCellValueFactory(new PropertyValueFactory<>("finalBalance"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        List<Cashier> cashierList = ucListCashier.listAllCashiers();
        tableData.clear();
        tableData.addAll(cashierList);
    }
    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLMain");
    }
}
