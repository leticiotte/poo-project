package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.model.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class UCOpenCashier {
    private DAOCashier daoCashier;

    public UCOpenCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public void openCashier(Double openingBalance) throws Exception {
        System.out.println(openingBalance);

        Cashier dbCashier = this.daoCashier.selectClosedCashier();

        System.out.println(dbCashier);

        Cashier cashier = null;

        if (dbCashier.getId() == null) {
            cashier.setId(1);
            cashier.setStatus(CashierStatusEnum.OPENED);
            cashier.setOpeningBalance(openingBalance);
            this.daoCashier.save(cashier);
        }
        else{
            dbCashier.setOpeningBalance(openingBalance);
            dbCashier.setStatus(CashierStatusEnum.OPENED);
            this.daoCashier.open(cashier);
        }
    }
}
