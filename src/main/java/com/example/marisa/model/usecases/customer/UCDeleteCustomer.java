package com.example.marisa.model.usecases.customer;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;

public class UCDeleteCustomer {
  private DAOCustomer daoCustomer;

  public UCDeleteCustomer(DAOCustomer daoCustomer) {
    this.daoCustomer = daoCustomer;
  }

  public void deleteCustomer(Customer customer) throws Exception {
    if (customer.getId() == null) {
      throw new Exception("Cliente não está cadastrado no sistema.");
    }

    this.daoCustomer.delete(customer.getId());
  }
}
