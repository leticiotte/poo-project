package com.example.marisa.controller;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.model.usecases.cashier.UCListClosedCashier;
import com.example.marisa.model.usecases.cashier.UCOpenCashier;
import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OpenCashierController {
    public TextField inpOpeningBalance;
    private UCOpenCashier ucOpenCashier;
    private UCListClosedCashier ucListClosedCashier;
    private Cashier cashier;

    public void openCashier(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) inpOpeningBalance.getScene().getWindow();
        stage.close();

        try {
            cashier = ucListClosedCashier.listClosedCashier();
        }
        catch (Exception e){
            showAlert("Erro!", "Nenhum caixa fechado.", Alert.AlertType.ERROR);
        }

        cashier.setOpeningBalance(Double.parseDouble(inpOpeningBalance.getText()));
        cashier.setStatus(CashierStatusEnum.OPENED);

        try {
            ucOpenCashier.openCashier(cashier);
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
