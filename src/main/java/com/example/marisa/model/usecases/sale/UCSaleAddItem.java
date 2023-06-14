package com.example.marisa.model.usecases.sale;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.model.usecases.exceptions.InvalidDiscountException;
import com.example.marisa.model.usecases.exceptions.OutOfStockException;
import com.example.marisa.persistence.dao.DAOSaleItem;

public class UCSaleAddItem {
    private DAOSaleItem daoSaleItem;

    public UCSaleAddItem(DAOSaleItem daoSaleItem) {
        this.daoSaleItem = daoSaleItem;
    }

    public void saleAddItem(Sale sale, SaleItem saleItem) throws Exception {
        if (!saleItem.isValidStock()) {
            throw new OutOfStockException("Produto com estoque indisponível.");
        }

        if (!saleItem.isValidDiscount()) {
            throw new InvalidDiscountException("Desconto inválido.");
        }

        if (daoSaleItem.select(sale.getId(), saleItem.getProduct().getId()).isPresent()){
            throw new Exception("Produto já está na venda.");
        }

        saleItem.calculatePriceAndDiscount();
        sale.addProduct(saleItem);

        this.daoSaleItem.save(saleItem);
    }
}
