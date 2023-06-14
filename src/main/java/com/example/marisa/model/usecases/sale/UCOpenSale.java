package com.example.marisa.model.usecases.sale;

import com.example.marisa.model.entities.Sale;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.persistence.dao.DAOCustomer;
import com.example.marisa.persistence.dao.DAOSale;

public class UCOpenSale {
    private DAOCashier daoCashier;
    private DAOCustomer daoCustomer;
    private DAOSale daoSale;

    public UCOpenSale(DAOSale daoSale, DAOCustomer daoCustomer, DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
        this.daoSale = daoSale;
        this.daoCustomer = daoCustomer;
    }

    public Integer openSale(Integer cashierId, String customerCpf) throws Exception {
        if (daoCashier.select(cashierId).isEmpty()) {
            throw new Exception("Caixa inválido.");
        }
        if (daoCustomer.select(customerCpf).isEmpty()) {
            throw new Exception("Cliente não cadastrado.");
        }

        Sale sale = new Sale(cashierId, customerCpf);
        this.daoSale.save(sale);
        return this.daoSale.selectLastIdGenerated();
    }
}
