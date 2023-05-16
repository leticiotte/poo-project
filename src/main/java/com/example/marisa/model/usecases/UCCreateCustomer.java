package com.example.marisa.model.usecases;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;
import com.example.marisa.persistence.utils.Validator;

public class UCCreateCustomer {
    private DAOCustomer daoCustomer;

    public UCCreateCustomer(DAOCustomer daoCustomer) {
        this.daoCustomer = daoCustomer;
    }

    public void createCustomer(Customer customer) {
        if (this.daoCustomer.select(customer.getId()).isPresent()) {
            throw new Error("Produto já cadastrado no sistema");
        }

        ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "name", "cpf", "phone", "email", "status",
                "number", "street", "complement", "city", "country", "zipcode"));
        if (!Validator.validateFields(customer, params)) {
            throw new Error("Produto não está com todos os campos obrigatórios preenchidos.");
        }

        this.daoCustomer.save(customer);
    }
}
