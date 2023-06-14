package com.example.marisa.model.usecases.sale;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.model.usecases.exceptions.InvalidDiscountException;
import com.example.marisa.model.usecases.exceptions.OutOfStockException;
import com.example.marisa.persistence.dao.DAOSaleItem;

public class UCSaleRemoveItem {
    private DAOSaleItem daoSaleItem;

    public UCSaleRemoveItem(DAOSaleItem daoSaleItem) {
        this.daoSaleItem = daoSaleItem;
    }

    public void saleAddItem(Integer saleId, Integer productId) throws Exception {
        if (daoSaleItem.select(saleId, productId).isEmpty()) {
            throw new Exception("Produto não está na venda.");
        }

        this.daoSaleItem.delete(saleId, productId);
    }
}
