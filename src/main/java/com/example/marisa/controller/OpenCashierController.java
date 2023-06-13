package com.example.marisa.controller;

import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenCashierController {
    public TextField inpOpeningBalance;

    public void openCashier(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) inpOpeningBalance.getScene().getWindow();
        stage.close();
        WindowLoader.setRoot("FXMLMain");
    }
}
