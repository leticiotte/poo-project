package com.example.marisa.model.usecases.customer;

import java.util.List;

import com.example.marisa.model.entities.Customer;
import com.example.marisa.persistence.dao.DAOCustomer;

public class UCListCustomers {
  private DAOCustomer daoCustomer;

  public UCListCustomers(DAOCustomer daoProduct) {
    this.daoCustomer = daoProduct;
  }

  public List<Customer> listCustomers() {
    return this.daoCustomer.selectAll();
  }

  public List<Customer> listCustomers(String filter, String value) {
    return this.daoCustomer.selectBy(filter, value);
  }
}
