package com.example.marisa.model.usecases.Product;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;

public class UCCreateProduct {
  private DAOProduct daoProduct;

  public UCCreateProduct(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public void createProduct(Product product) {
    if (this.daoProduct.select(product.getId()).isPresent()) {
      throw new Error("Produto já cadastrado no sistema");
    }

    if (!product.validateFields()) {
      throw new Error("Produto não está com todos os campos obrigatórios preenchidos.");
    }

    this.daoProduct.save(product);
  }
}
