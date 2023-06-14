package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.persistence.dao.DAOCashier;

public class UCListClosedCashier {
    private DAOCashier daoCashier;

    public UCListClosedCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public Cashier listClosedCashier(){
        return this.daoCashier.selectClosedCashier();
    }
}
