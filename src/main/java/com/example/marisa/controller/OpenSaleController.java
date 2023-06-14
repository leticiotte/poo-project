package com.example.marisa.controller;

import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class OpenSaleController {
    public TextField inpCashierId;
    public TextField inpCustomerCpf;

    public void openSale(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLSale");
        SaleController saleController = (SaleController) WindowLoader.getController();
        saleController.setCashierId(Integer.valueOf(inpCashierId.getText()));
        saleController.setCustomerCpf(inpCustomerCpf.getText());

        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        Window currentWindow = inpCashierId.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            currentStage.close();
        }
    }
}
