package com.example.marisa.controller;

import com.example.marisa.model.entities.KeyValuePair;
import com.example.marisa.model.entities.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import static com.example.marisa.main.Main.ucListProducts;

public class SaleController {
    public ChoiceBox<KeyValuePair> selProducts;
    public TextField inpQuantity;
    public TextField inpDiscount;

    public Integer cashierId;
    public String customerCpf;

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    @FXML
    private void initialize(){
        setInpDiscount();
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
    }

    private void bindColumnsToValueSources() {
        
    }

    private void loadDataAndShow() {
        
    }

    private void setInpDiscount(){
        List<Product> productList = ucListProducts.listProducts();
        List<KeyValuePair> productsToSelect = new ArrayList<>();
        for(Product product : productList){
            productsToSelect.add(new KeyValuePair(product.getId(), product.getName()));
        }
        for (KeyValuePair item : productsToSelect){
            selProducts.getItems().add(item);
        }
    }

    public void addProduct(ActionEvent actionEvent) {
//        Sale sale = new Sale();
//        ucSaleAddItem.saleAddItem();
    }

    public void deleteProduct(ActionEvent actionEvent) {
    }

    public void closeSale(ActionEvent actionEvent) {
    }

    public void backToPreviousScene(ActionEvent actionEvent) {
    }
}
