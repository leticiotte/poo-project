package com.example.marisa.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class ChargeBackController {
    public void chargeBack(ActionEvent actionEvent) throws Exception{


        try{

        }
        catch (Exception e){
            showAlert("Erro!", "Erro ao realizar estorno.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
