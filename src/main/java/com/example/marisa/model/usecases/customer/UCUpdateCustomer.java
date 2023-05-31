package com.example.marisa.model.usecases.customer;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;
import com.example.marisa.persistence.utils.Validator;

public class UCUpdateCustomer {
  private DAOCustomer daoCustomer;

  public UCUpdateCustomer(DAOCustomer daoCustomer) {
    this.daoCustomer = daoCustomer;
  }

  public void updateCustomer(Customer customer) throws Exception {
    ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "name", "cpf", "phone", "email", "status",
        "number", "street", "complement", "city", "country", "zipcode"));

    if (!Validator.validateFields(customer, params)) {
      throw new Exception("Cliente não está com todos os campos obrigatórios preenchidos.");
    }

    this.daoCustomer.saveOrUpdate(customer);
  }
}
