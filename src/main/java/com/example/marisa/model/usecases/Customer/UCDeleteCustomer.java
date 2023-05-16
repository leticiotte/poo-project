package com.example.marisa.model.usecases.Customer;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;

public class UCDeleteCustomer {
  private DAOCustomer daoCustomer;

  public UCDeleteCustomer(DAOCustomer daoCustomer) {
    this.daoCustomer = daoCustomer;
  }

  public void deleteCustomer(Customer customer) {
    if (customer.getId() == null) {
      throw new Error("Cliente não está cadastrado no sistema.");
    }

    this.daoCustomer.delete(customer.getId());
  }
}
