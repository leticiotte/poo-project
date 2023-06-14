package com.example.marisa.model.usecases.chargeBack;

import com.example.marisa.model.entities.Product;
import com.example.marisa.model.entities.Sale;
import com.example.marisa.persistence.dao.DAOProduct;
import com.example.marisa.persistence.dao.DAOSale;

import java.util.List;

public class UCChargeBack {
    private DAOSale daoSale;
    private DAOProduct daoProduct;

    public void ChargeBack(Sale sale, List<Product> products){
        //TODO: implementar sale

        for(Product product : products) this.daoProduct.updateQuantity(product);
    }


}
