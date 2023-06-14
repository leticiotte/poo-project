package com.example.marisa.controller;

import static com.example.marisa.main.Main.*;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.view.WindowLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class CustomerManagementController {
    @FXML
    public TableView<Customer> tableView;
    @FXML
    public TableColumn<Customer, String> cName;
    @FXML
    public TableColumn<Customer, String> cCpf;
    @FXML
    public TableColumn<Customer, String> cPhone;
    @FXML
    public TableColumn<Customer, String> cEmail;
    @FXML
    public TableColumn<Customer, String> cZipcode;
    @FXML
    public TableColumn<Customer, String> cCity;

    public TextField inpSearch;

    private ObservableList<Customer> tableData;


    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cZipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        cCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    private void loadDataAndShow() {
        List<Customer> customerList = ucListCustomers.listCustomers();
        tableData.clear();
        tableData.addAll(customerList);
    }

    public void addCustomer(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLCustomer");
        CustomerController customerController = (CustomerController) WindowLoader.getController();
        customerController.setMode(Mode.CREATE);
    }

    public void editCustomer(ActionEvent actionEvent) throws IOException {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        if (customer != null) {
            WindowLoader.setRoot("FXMLCustomer");
            CustomerController customerController = (CustomerController) WindowLoader.getController();
            customerController.setCustomer(customer, Mode.UPDATE);
        } else {
            showAlert("Erro!", "Nenhum cliente selecionado.", Alert.AlertType.ERROR);
        }
    }

    public void deleteCustomer(ActionEvent actionEvent){
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        if (customer != null) {
            try {
                ucDeleteCustomer.deleteCustomer(customer.getCpf());
                loadDataAndShow();
            } catch (Exception e) {
                showAlert("Erro!", "Erro ao excluir o cliente", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Erro!", "Nenhum cliente selecionado.", Alert.AlertType.ERROR);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("FXMLMain");
    }

    public void search(ActionEvent actionEvent) {
        String filterText = inpSearch.getCharacters().toString();

        if (filterText.isEmpty()) {
            loadDataAndShow();
            return;
        }

        List<Customer> customerList = ucListCustomers.listCustomers("name", filterText.toUpperCase());
        tableData.clear();
        tableData.addAll(customerList);
    }

    public void export(ActionEvent actionEvent) {
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
