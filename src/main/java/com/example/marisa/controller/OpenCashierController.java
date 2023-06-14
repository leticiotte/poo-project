package com.example.marisa.controller;

import static com.example.marisa.main.Main.ucOpenCashier;
import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class OpenCashierController {
    public TextField inpOpeningBalance;

    public void openCashier(ActionEvent actionEvent) throws Exception {
        Double openingBalanceCharacters = Double.parseDouble(inpOpeningBalance.getText());

        try {
            ucOpenCashier.openCashier(openingBalanceCharacters);
        }
        catch (Exception e){
            showAlert("Erro!", "Erro ao abrir caixa.", Alert.AlertType.ERROR);
        }
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
