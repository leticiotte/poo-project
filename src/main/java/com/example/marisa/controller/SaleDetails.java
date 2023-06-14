package com.example.marisa.controller;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.view.WindowLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

public class SaleDetails {
    @FXML
    public TextField inpSearch;
    @FXML
    private TableView<SaleItem> tableView;
    @FXML
    private TableColumn<SaleItem, String> cId;
    @FXML
    private TableColumn<SaleItem, String> cName;
    @FXML
    private TableColumn<SaleItem, String> cBuyPrice;
    @FXML
    private TableColumn<SaleItem, String> cSellPrice;
    @FXML
    private TableColumn<SaleItem, String> cQuantity;
    @FXML
    private TableColumn<SaleItem, String> cDiscount;
    @FXML
    private TableColumn<SaleItem, String> cPayablePrice;
    @FXML
    private TableColumn<SaleItem, String> cDiscountValue;
    private ObservableList<SaleItem> tableData;

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
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        cSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        cPayablePrice.setCellValueFactory(new PropertyValueFactory<>("payablePrice"));
        cDiscountValue.setCellValueFactory(new PropertyValueFactory<>("discountValue"));
    }

    private void loadDataAndShow() {
//        List<SaleItem> saleItems = ucListProducts.listProducts();
        tableData.clear();
//        tableData.addAll(productList);
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLProduct");
        ProductController productController = (ProductController) WindowLoader.getController();
        productController.setMode(Mode.CREATE);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLMain");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
