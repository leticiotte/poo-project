package com.example.marisa.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import static com.example.marisa.main.Main.ucCloseCashier;

public class CloseCashierController {
    public TextField inpFinalBalance;
    public TextField inpCashierId;

    public void closeCashier(ActionEvent actionEvent) throws Exception {
        Double finalBalance = Double.parseDouble(inpFinalBalance.getText());
        Integer cashierId = Integer.parseInt(inpCashierId.getText());

        try {
            ucCloseCashier.closeCashier(finalBalance, cashierId);
        }
        catch (Exception e){
            showAlert("Erro!", "Erro ao fechar caixa.", Alert.AlertType.ERROR);
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
        Window currentWindow = inpFinalBalance.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            currentStage.close();
        }
    }
}
