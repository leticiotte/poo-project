package com.example.marisa.controller;
import static com.example.marisa.main.Main.*;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.model.usecases.customer.UCCreateCustomer;
import com.example.marisa.model.usecases.customer.UCUpdateCustomer;
import com.example.marisa.view.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerController {
    @FXML
    public TextField inpName;
    @FXML
    public TextField inpCpf;
    @FXML
    public TextField inpPhone;
    @FXML
    public TextField inpEmail;
    @FXML
    public TextField inpStreet;
    @FXML
    public TextField inpNumber;
    @FXML
    public TextField inpComplement;
    @FXML
    public TextField inpCity;
    @FXML
    public TextField inpCountry;
    @FXML
    public TextField inpZipcode;

    private Mode mode;
    private Customer customer;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if(mode.equals(Mode.CREATE)) {
            try {
                ucCreateCustomer.createCustomer(customer);
                WindowLoader.setRoot("FXMLCustomerManagement");
            } catch (Exception e) {
                showAlert("Erro!", "Erro ao criar cliente: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else{
            try {
                ucUpdateCustomer.updateCustomer(customer);
                WindowLoader.setRoot("FXMLCustomerManagement");
            } catch (Exception e) {
                showAlert("Erro!", "Erro ao atualizar cliente: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLCustomerManagement");
    }

    private void setEntityIntoView(){
        inpName.setText(customer.getName());
        inpCpf.setText(customer.getCpf());
        inpPhone.setText(customer.getPhone());
        inpEmail.setText(customer.getEmail());
        inpStreet.setText(customer.getStreet());
        inpNumber.setText(String.valueOf(customer.getNumber()));
        inpComplement.setText(customer.getComplement());
        inpCity.setText(customer.getCity());
        inpCountry.setText(customer.getCountry());
        inpZipcode.setText(customer.getZipcode());
    }

    private void getEntityToView(){
        if (customer == null) {
            customer = new Customer();
        }

        if(!inpName.getText().isEmpty()){
            customer.setName(inpName.getText());
        }
        if (!inpCpf.getText().isEmpty()) {
            customer.setCpf(inpCpf.getText());
        }
        if (!inpPhone.getText().isEmpty()) {
            customer.setPhone(inpPhone.getText());
        }
        if (!inpEmail.getText().isEmpty()) {
            customer.setEmail(inpEmail.getText());
        }
        if (!inpStreet.getText().isEmpty()) {
            customer.setStreet(inpStreet.getText());
        }
        if (!inpNumber.getText().isEmpty()) {
            customer.setNumber(Integer.valueOf(inpNumber.getText()));
        }
        if (!inpComplement.getText().isEmpty()) {
            customer.setComplement(inpComplement.getText());
        }
        if (!inpCity.getText().isEmpty()) {
            customer.setCity(inpCity.getText());
        }
        if (!inpCountry.getText().isEmpty()) {
            customer.setCountry(inpCountry.getText());
        }
        if (!inpZipcode.getText().isEmpty()) {
            customer.setZipcode(inpZipcode.getText());
        }

    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }

    public void setCustomer(Customer customer, Mode mode) {
        if(customer == null)
            throw new IllegalArgumentException("Customer can not be null.");

        this.customer = customer;
        this.mode = mode;
        setEntityIntoView();
    }
}
