package com.example.marisa.model.usecases.chargeBack;

import com.example.marisa.model.entities.Product;
import com.example.marisa.persistence.dao.DAOSale;

import java.util.List;

public class UCChargeBack {
    private DAOSale daoSale;

    public UCChargeBack(DAOSale daoSale) {
        this.daoSale = daoSale;
    }

    public void chargeBack(Integer saleId, List<Product> products){

    }
}
