package com.example.marisa.model.usecases.product;

import java.util.List;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOProduct;

public class UCListProducts {
  private DAOProduct daoProduct;

  public UCListProducts(DAOProduct daoProduct) {
    this.daoProduct = daoProduct;
  }

  public List<Product> listProducts() {
    return this.daoProduct.selectAll();
  }

  public List<Product> listProducts(String filter, String value) {
    return this.daoProduct.selectBy(filter, value);
  }
}
