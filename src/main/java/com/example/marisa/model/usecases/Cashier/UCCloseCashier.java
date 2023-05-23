package com.example.marisa.model.usecases.Cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.persistence.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;

public class UCCloseCashier {
    private DAOCashier daoCashier;

    public UCCloseCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public void closeCashier(Cashier cashier) {
        if (this.daoCashier.select(cashier.getId()).isEmpty()) {
            throw new Error("O Caixa não está aberto");
        }

        ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "finalBalance", "status"));
        if (!Validator.validateFields(cashier, params)) {
            throw new Error("O Caixa não está com todos os campos obrigatórios preenchidos.");
        }

        this.daoCashier.update(cashier);
    }
}
