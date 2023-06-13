package com.example.marisa.controller;

import com.example.marisa.view.WindowLoader;
import com.example.marisa.model.entities.Product;
import com.example.marisa.model.usecases.product.UCDeleteProduct;
import com.example.marisa.model.usecases.product.UCListProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ProductManagementController {
    @FXML
    public TextField inpSearch;
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> cId;
    @FXML
    private TableColumn<Product, String> cName;
    @FXML
    private TableColumn<Product, String> cBuyPrice;
    @FXML
    private TableColumn<Product, String> cSellPrice;
    @FXML
    private TableColumn<Product, String> cSize;
    @FXML
    private TableColumn<Product, String> cFacet;
    @FXML
    private TableColumn<Product, String> cCategory;
    @FXML
    private TableColumn<Product, String> cMinimumStock;
    @FXML
    private TableColumn<Product, LocalDate> cCreationDate;

    private UCListProducts ucListProducts;
    private UCDeleteProduct ucDeleteProduct;

    private ObservableList<Product> tableData;

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
        cSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        cFacet.setCellValueFactory(new PropertyValueFactory<>("facet"));
        cCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        cMinimumStock.setCellValueFactory(new PropertyValueFactory<>("minimumStock"));
        cCreationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        cCreationDate.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                if (empty) {
                    setText("");
                } else {
                    setText(formatter.format(date));
                }
            }
        });
    }

    private void loadDataAndShow() {
        List<Product> productList = Arrays.asList(
                new Product(1, "Camiseta", 29.99, 15.0, 100, "M", "Algodão", "Vestuário", 20, LocalDate.now()),
                new Product(2, "Calça", 59.99, 30.0, 50, "G", "Jeans", "Vestuário", 10, LocalDate.now()),
                new Product(3, "Tênis", 99.99, 70.0, 20, "42", "Couro", "Calçados", 5, LocalDate.now())
        );
        tableData.addAll(productList);

//        List<Product> productList = ucListProducts.listProducts();
//        tableData.clear();
//        tableData.addAll(productList);
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLProduct");
        ProductController productController = (ProductController) WindowLoader.getController();
        productController.setMode(Mode.CREATE);
    }

    public void editProduct(ActionEvent actionEvent) throws IOException {
        Product product = tableView.getSelectionModel().getSelectedItem();
        if (product != null) {
            WindowLoader.setRoot("FXMLProduct");
            ProductController productController = (ProductController) WindowLoader.getController();
            productController.setProduct(product, Mode.UPDATE);
        } else {
            showAlert("Erro!", "Nenhum produto selecionado.", Alert.AlertType.ERROR);
        }
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product product = tableView.getSelectionModel().getSelectedItem();
        if (product != null) {
            try {
                ucDeleteProduct.deleteProduct(product.getId());
                loadDataAndShow();
            } catch (Exception e) {
                showAlert("Erro!", "Erro ao excluir o produto", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Erro!", "Nenhum produto selecionado.", Alert.AlertType.ERROR);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLMain");
    }

    public void search(ActionEvent actionEvent) {
    }

    public void export(ActionEvent actionEvent) {
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
