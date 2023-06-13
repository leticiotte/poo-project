package com.example.marisa.controller;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.model.entities.Product;
import com.example.marisa.model.usecases.customer.UCDeleteCustomer;
import com.example.marisa.model.usecases.customer.UCListCustomers;
import com.example.marisa.model.usecases.product.UCDeleteProduct;
import com.example.marisa.model.usecases.product.UCListProducts;
import com.example.marisa.view.WindowLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
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

    private ObservableList<Customer> tableData;

    private UCListCustomers ucListCustomers;
    private UCDeleteCustomer ucDeleteCustomer;


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
        List<Customer> customerList = Arrays.asList(
                new Customer("João da Silva", "123.456.789-00", "(11) 99999-9999", "joao@email.com", "Ativo",
                        10, "Rua das Flores", "Apto 123", "São Paulo", "Brasil", "01234-567"),
                new Customer("Maria Souza", "987.654.321-00", "(22) 88888-8888", "maria@email.com",
                        "Inativo", 20, "Avenida das Palmeiras", "Casa 456", "Rio de Janeiro", "Brasil", "76543-210")
        );
        tableData.addAll(customerList);

//        List<Customer> customerList = ucListCustomers.listCustomers();
//        tableData.clear();
//        tableData.addAll(customerList);
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
