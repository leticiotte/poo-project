package com.example.marisa.model.usecases.sale;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.model.entities.SaleItem;
import com.example.marisa.persistence.dao.DAOSale;

import java.util.List;

public class UCSaleList {
    private DAOSale daoSale;

    public UCSaleList(DAOSale daoSale) {
        this.daoSale = daoSale;
    }

    public List<Sale> listSales(){
        return this.daoSale.selectAll();
    }
}
