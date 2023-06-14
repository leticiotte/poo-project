package com.example.marisa.model.usecases.saleItem;

import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.persistence.dao.DAOSaleItem;

import java.util.List;

public class UCListSaleItems {
  private DAOSaleItem daoSaleItem;

  public UCListSaleItems(DAOSaleItem daoSaleItem) {
    this.daoSaleItem = daoSaleItem;
  }

  public List<SaleItem> listProducts() {
    return this.daoSaleItem.selectAll();
  }
}
