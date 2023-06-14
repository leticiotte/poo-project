package com.example.marisa.model.usecases.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;
import com.example.marisa.model.utils.Validator;

public class UCUpdateCustomer {
  private DAOCustomer daoCustomer;

  public UCUpdateCustomer(DAOCustomer daoCustomer) {
    this.daoCustomer = daoCustomer;
  }

  public void updateCustomer(Customer customer) throws Exception {
      ArrayList<String> params = new ArrayList<>(Arrays.asList("name", "cpf", "phone", "email",
              "number", "street", "complement", "city", "country", "zipcode"));

      if (!Validator.validateFields(customer, params)) {
        throw new Exception("Cliente não está com todos os campos obrigatórios preenchidos.");
      }

      Optional<Customer> getCustomer = this.daoCustomer.select(customer.getCpf());
      if(getCustomer.isEmpty()) throw new Exception("Cpf não pode ser alterado.");

      if(!customer.validateEmail()) throw new Exception("Email inválido.");

      this.daoCustomer.saveOrUpdate(customer);
  }
}
