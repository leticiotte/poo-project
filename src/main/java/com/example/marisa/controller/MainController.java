package com.example.marisa.controller;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class MainController {
    public Button openCashier;
    public Button closeCashier;
    public Button openSale;
    public TableView<Sale> tableView;
    public TableColumn<Sale, String> cId;
    public TableColumn<Sale, String> cCpf;
    public TableColumn<Sale, String> cDate;
    public TableColumn<Sale, String> cTotalValue;
    public TableColumn<Sale, String> cTotalDiscount;
    public TableColumn<Sale, String> cStatus;
    public TableColumn<Sale, String> cPaymentType;

    @FXML
    private void initialize() {
        //validar status do caixa - mudar disable dos botões
        //se caixa aberto, carregar vendas
    }
    public void cashierManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLCashierManagement");
    }

    public void productsManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLProductManagement");

    }

    public void customerManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLCustomerManagement");
    }

    public void salesManagement(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLSaleManagement");
    }

    public void openCashier(ActionEvent actionEvent) throws IOException {
        WindowLoader.openNewWindow("FXMLOpenCashier", "Abertura de caixa");
    }

    public void closeCashier(ActionEvent actionEvent) throws IOException {
        WindowLoader.openNewWindow("FXMLCloseCashier", "Fechar caixa");
    }

    public void openSale(ActionEvent actionEvent) {
    }
}
