package com.example.marisa.model.usecases.product;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;

public class UCUpdateProduct {
  private DAOProduct daoProduct;

  public UCUpdateProduct(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public void updateProduct(Product product) throws Exception {
    if (!product.validateFields()) {
      throw new Exception("Produto não está com todos os campos obrigatórios preenchidos.");
    }

    this.daoProduct.saveOrUpdate(product);
  }
}
