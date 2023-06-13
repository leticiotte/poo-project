package com.example.marisa.model.usecases.customer;

import com.example.marisa.persistence.dao.DAOCustomer;

public class UCDeleteCustomer {
  private DAOCustomer daoCustomer;

  public UCDeleteCustomer(DAOCustomer daoCustomer) {
    this.daoCustomer = daoCustomer;
  }

  public void deleteCustomer(String cpf) throws Exception {
    if (daoCustomer.select(cpf).isEmpty()) {
      throw new Exception("Cliente não está cadastrado no sistema.");
    }

    this.daoCustomer.delete(cpf);
  }
}
