package com.example.marisa.controller;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.usecases.sale.UCSaleAddItem;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static com.example.marisa.main.Main.ucSaleAddItem;

public class SaleController {
    public ChoiceBox selProducts;
    public TextField inpQuantity;
    public TextField inpDiscount;

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
