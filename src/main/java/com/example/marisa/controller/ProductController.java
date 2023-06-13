package com.example.marisa.controller;

import com.example.marisa.model.entities.Product;
import com.example.marisa.model.usecases.product.UCCreateProduct;
import com.example.marisa.model.usecases.product.UCUpdateProduct;
import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProductController {
    @FXML
    public TextField inpName;
    @FXML
    public TextField inpBuyPrice;
    @FXML
    public TextField inpSellPrice;
    @FXML
    public TextField inpSize;
    @FXML
    public TextField inpFacet;
    @FXML
    public TextField inpCategory;
    @FXML
    public TextField inpMinimumStock;
    @FXML
    public TextField inpStock;

    private Mode mode;
    private Product product;
    private UCCreateProduct ucCreateProduct;
    private UCUpdateProduct ucUpdateProduct;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if(mode.equals(Mode.CREATE)) {
            try {
                ucCreateProduct.createProduct(product);
            } catch (Exception e) {
                showAlert("Erro!", "Erro ao criar produto.", Alert.AlertType.ERROR);
            }
        }
        else{
            try {
                ucUpdateProduct.updateProduct(product);
            } catch (Exception e) {
                showAlert("Erro!", "Erro ao atualizar produto.", Alert.AlertType.ERROR);
            }
        }
        WindowLoader.setRoot("FXMLProductManagement");
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLProductManagement");
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }
    public void setProduct(Product product, Mode mode) {
        if(product == null)
            throw new IllegalArgumentException("Product can not be null.");

        this.product = product;
        this.mode = mode;
        setEntityIntoView();
    }

    private void setEntityIntoView(){
        inpName.setText(product.getName());
        inpBuyPrice.setText(String.valueOf(product.getBuyPrice()));
        inpSellPrice.setText(String.valueOf(product.getSellPrice()));
        inpSize.setText(product.getSize());
        inpFacet.setText(product.getFacet());
        inpCategory.setText(product.getCategory());
        inpMinimumStock.setText(String.valueOf(product.getMinimumStock()));
        inpStock.setText(String.valueOf(product.getQuantity()));
    }

    private void getEntityToView(){
        if (product == null) {
            product = new Product();
        }
        product.setName(inpName.getText());
        product.setBuyPrice(Double.parseDouble(inpBuyPrice.getText()));
        product.setSellPrice(Double.parseDouble(inpSellPrice.getText()));
        product.setSize(inpSize.getText());
        product.setFacet(inpFacet.getText());
        product.setCategory(inpCategory.getText());
        product.setMinimumStock(Integer.valueOf(inpMinimumStock.getText()));
        product.setQuantity(Integer.valueOf(inpStock.getText()));
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
