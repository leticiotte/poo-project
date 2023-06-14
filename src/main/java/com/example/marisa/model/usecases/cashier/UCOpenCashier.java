package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.model.utils.Validator;
import com.example.marisa.persistence.dao.DAOCashier;

import java.util.ArrayList;
import java.util.Arrays;

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

        } else if (dbCashier.getStatus() == CashierStatusEnum.OPENED) {
            throw new Exception("O Caixa já está aberto.");
        }
        else{
            dbCashier.setOpeningBalance(openingBalance);
            dbCashier.setStatus(CashierStatusEnum.OPENED);

            ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "openingBalance", "status"));
            if (!Validator.validateFields(dbCashier, params)) {
                throw new Exception("O Caixa não está com todos os campos obrigatórios preenchidos.");
            }

            this.daoCashier.open(dbCashier);
        }
    }
}
