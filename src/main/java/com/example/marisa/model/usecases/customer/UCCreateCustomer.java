package com.example.marisa.model.usecases.customer;

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

    public void createCustomer(Customer customer) throws Exception {
        if (this.daoCustomer.select(customer.getId()).isPresent()) {
            throw new Exception("Cliente já cadastrado no sistema");
        }

        if ((this.daoCustomer.selectBy("cpf", customer.getCpf())) != null) {
            throw new Exception("CPF já cadastrado no sistema");
        }

        ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "name", "cpf", "phone", "email", "status",
                "number", "street", "complement", "city", "country", "zipcode"));

        if (!Validator.validateFields(customer, params)) {
            throw new Exception("Cliente não está com todos os campos obrigatórios preenchidos.");
        }

        this.daoCustomer.save(customer);
    }
}
