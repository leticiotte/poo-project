package com.example.marisa.model.usecases.customer;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;
import com.example.marisa.model.utils.Validator;

public class UCCreateCustomer {
    private DAOCustomer daoCustomer;

    public UCCreateCustomer(DAOCustomer daoCustomer) {
        this.daoCustomer = daoCustomer;
    }

    public void createCustomer(Customer customer) throws Exception {
        if (this.daoCustomer.select(customer.getCpf()).isPresent()) {
            throw new Exception("Cliente já cadastrado no sistema");
        }

        ArrayList<String> params = new ArrayList<>(Arrays.asList("name", "cpf", "phone", "email",
                "number", "street", "complement", "city", "country", "zipcode"));

        if (!Validator.validateFields(customer, params)) {
            throw new Exception("Todos os campos devem ser preenchidos.");
        }

        if(!customer.validateCPF()) throw new Exception("Cpf inválido");

        if(!customer.validateEmail()) throw new Exception("Email inválido.");

        this.daoCustomer.save(customer);
    }
}
