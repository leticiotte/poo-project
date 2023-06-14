package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.model.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UCOpenCashier {
    private DAOCashier daoCashier;

    public UCOpenCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public void openCashier(Double openingBalance, Integer cashierId) throws Exception {
        Cashier dbCashier = this.daoCashier.selectById(cashierId);

        if (dbCashier.getId() == null) {
            Cashier cashier = new Cashier();
            cashier.setId(cashierId);
            cashier.setStatus(CashierStatusEnum.OPENED);
            cashier.setOpeningBalance(openingBalance);

            this.daoCashier.save(cashier);

        } else if (dbCashier.getStatus() == CashierStatusEnum.CLOSED) {
            dbCashier.setOpeningBalance(openingBalance);
            dbCashier.setStatus(CashierStatusEnum.OPENED);

            this.daoCashier.open(dbCashier);
        }
    }
}
