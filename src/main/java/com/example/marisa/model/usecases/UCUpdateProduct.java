package com.example.marisa.model.usecases;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;

public class UCUpdateProduct {
  private DAOProduct daoProduct;

  public UCUpdateProduct(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public void updateProduct(Product product) {
    if (!product.validateFields()) {
      throw new Error("Produto não está com todos os campos obrigatórios preenchidos.");
    }

    this.daoProduct.saveOrUpdate(product);
  }
}
