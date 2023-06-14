package com.example.marisa.controller;

import static com.example.marisa.main.Main.ucOpenCashier;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OpenCashierController {
    public TextField inpOpeningBalance;
    public TextField inpCashierId;

    public void openCashier(ActionEvent actionEvent) throws Exception {
        Double openingBalance = Double.parseDouble(inpOpeningBalance.getText());
        Integer cashierId = Integer.parseInt(inpCashierId.getText());

        try {
            ucOpenCashier.openCashier(openingBalance, cashierId);
        }
        catch (Exception e){
            showAlert("Erro!", "Erro ao abrir caixa.", Alert.AlertType.ERROR);
        }
        closeCurrentWindow();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void closeCurrentWindow() {
        Window currentWindow = inpOpeningBalance.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            currentStage.close();
        }
    }
}
