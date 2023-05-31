package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.model.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class UCCloseCashier {
    private DAOCashier daoCashier;

    public UCCloseCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public void closeCashier(Cashier cashier) throws Exception {
        Optional<Cashier> dbCashier = this.daoCashier.select(cashier.getId());

        if (dbCashier.isEmpty()) {
            throw new Exception("O Caixa não existe.");
        }

        if (cashier.getStatus() == CashierStatusEnum.CLOSED) {
            throw new Exception("O Caixa já está fechado.");
        }

        ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "finalBalance", "status"));
        if (!Validator.validateFields(cashier, params)) {
            throw new Exception("O Caixa não está com todos os campos obrigatórios preenchidos.");
        }

        this.daoCashier.close(cashier);
    }
}
