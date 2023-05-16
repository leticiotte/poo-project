package com.example.marisa.model.usecases;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;
import com.example.marisa.persistence.dao.DAOSale;

public class UCDeleteProduct {
  private DAOProduct daoProduct;
  private DAOSale daoSale;

  public UCDeleteProduct(DAOProduct daoProduct, DAOSale daoSale) {
    this.daoProduct = daoProduct;
    this.daoSale = daoSale;
  }

  public void deleteProduct(Product product) {
    if (product.getId() == null) {
      throw new Error("Produto não está cadastrado no sistema.");
    }

    if (!daoSale.selectSalesByProduct(product).isEmpty()) {
      throw new Error("Não é possível deletar o produto pois ele já está registrado em uma venda");
    }

    this.daoProduct.delete(product.getId());
  }
}
