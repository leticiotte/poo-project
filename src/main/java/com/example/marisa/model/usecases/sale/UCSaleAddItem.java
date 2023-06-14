package com.example.marisa.model.usecases.sale;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.persistence.dao.DAOSale;

public class UCSaleAddItem {
    private DAOSale daoSale;

    public UCSaleAddItem(DAOSale daoSale) {
        this.daoSale = daoSale;
    }

    public void saleAddItem(Sale sale, SaleItem saleItem) throws Exception {
        if (!saleItem.isValidDiscount()) {
            throw new Exception("Produto com estoque indisponível.");
        }

        saleItem.calculatePriceAndDiscount();
        sale.addProduct(saleItem);

        this.daoSale.save(sale);
    }
}
