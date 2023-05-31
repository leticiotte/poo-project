package com.example.marisa.model.usecases.sale;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.enumeration.PaymentMethodTypeEnum;
import com.example.marisa.persistence.dao.DAOSale;

public class UCCloseSale {
    private DAOSale daoSale;

    public UCCloseSale(DAOSale daoSale) {
        this.daoSale = daoSale;
    }

    public void closeSale(Sale sale, PaymentMethodTypeEnum paymentType) throws Exception {
        if (!sale.isProductsStockValid()) {
            throw new Exception("Produto(s) com estoque indisponível.");
        }

        sale.closeSale(paymentType);

        this.daoSale.save(sale);
    }
}
