package com.example.marisa.model.usecases.Cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.persistence.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;

public class UCOpenCashier {
    private DAOCashier daoCashier;

    public UCOpenCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public void openCashier(Cashier cashier) {
        if (this.daoCashier.select(cashier.getId()).isPresent()) {
            throw new Error("O Caixa já aberto");
        }

        ArrayList<String> params = new ArrayList<>(Arrays.asList("openingBalance", "status"));
        if (!Validator.validateFields(cashier, params)) {
            throw new Error("O Caixa não está com todos os campos obrigatórios preenchidos.");
        }

        this.daoCashier.save(cashier);
    }
}
