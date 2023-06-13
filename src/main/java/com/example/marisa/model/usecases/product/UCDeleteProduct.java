package com.example.marisa.model.usecases.product;

import com.example.marisa.persistence.dao.DAOProduct;

public class UCDeleteProduct {
  private DAOProduct daoProduct;

  public UCDeleteProduct(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public void deleteProduct(Integer id) throws Exception {
    if (daoProduct.select(id).isEmpty()) {
      throw new Exception("Produto não está cadastrado no sistema.");
    }

    this.daoProduct.delete(id);
  }
}
