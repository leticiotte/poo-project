package com.example.marisa.model.usecases;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;

public class UCDeleteProduct {
  private DAOProduct daoProduct;

  // TODO: Injetar DAO de vendas
  public UCDeleteProduct(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public void deleteProduct(Product product) {
    if (product.getId() == null) {
      throw new Error("Produto não está cadastrado no sistema.");
    }

    // TODO: Implementar integração que verifica se produto está em uma venda

    this.daoProduct.delete(product.getId());
  }
}
