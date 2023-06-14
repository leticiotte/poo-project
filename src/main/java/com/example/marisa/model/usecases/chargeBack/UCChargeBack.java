package com.example.marisa.model.usecases.chargeBack;

import com.example.marisa.model.entities.ChargeBack;
import com.example.marisa.model.entities.Product;
import com.example.marisa.model.enumeration.SaleStatusEnum;
import com.example.marisa.persistence.dao.DAOSale;
import com.example.marisa.persistence.dao.DAOSaleItem;

import java.util.List;

public class UCChargeBack {
    private DAOSale daoSale;
    private DAOSaleItem daoSaleItem;

    public UCChargeBack(DAOSale daoSale, DAOSaleItem daoSaleItem) {
        this.daoSale = daoSale;
        this.daoSaleItem = daoSaleItem;
    }

    public void chargeBack(Integer saleId, List<Product> products){
        for(Product product : products){

        }

        this.daoSale.updateSaleStatus(saleId, String.valueOf(SaleStatusEnum.CHARGEBACK));
    }
}
