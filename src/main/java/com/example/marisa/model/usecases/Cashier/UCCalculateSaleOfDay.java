package com.example.marisa.model.usecases.Cashier;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.persistence.dao.DAOSale;

import java.sql.Date;
import java.util.List;

public class UCCalculateSaleOfDay {
    private DAOSale daoSale;

    public UCCalculateSaleOfDay(DAOSale daoSale) {
        this.daoSale = daoSale;
    }

    public List<Sale> calculateSaleOfDay(int id, Date date){
        return this.daoSale.selectCashierSales(id, date);
    }
}
