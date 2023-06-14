package com.example.marisa.model.usecases.product;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;

public class UCCreateProduct {
  private DAOProduct daoProduct;

  public UCCreateProduct(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public void createProduct(Product product) throws Exception {
    System.out.println(product.getName() + product.getCategory());
    if (product.getId() != null && this.daoProduct.select(product.getId()).isPresent()) {
      throw new Exception("Produto já cadastrado no sistema");
    }

    if (!product.validateFields()) {
      throw new Exception("Produto não está com todos os campos obrigatórios preenchidos.");
    }

    this.daoProduct.save(product);
  }
}
