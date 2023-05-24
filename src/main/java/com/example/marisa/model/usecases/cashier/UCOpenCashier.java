package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.model.enumeration.CashierStatusEnum;
import com.example.marisa.persistence.dao.DAOCashier;
import com.example.marisa.persistence.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class UCOpenCashier {
    private DAOCashier daoCashier;

    public UCOpenCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public void openCashier(Cashier cashier) {
        Optional<Cashier> dbCashier = this.daoCashier.select(cashier.getId());

        ArrayList<String> params = new ArrayList<>(Arrays.asList("id", "openingBalance", "status"));
        if (!Validator.validateFields(cashier, params)) {
            throw new Error("O Caixa não está com todos os campos obrigatórios preenchidos.");
        }

        if(cashier.getStatus() == CashierStatusEnum.OPENED){
            throw new Error("O Caixa já está aberto.");
        }

        if (dbCashier.isEmpty()) {
            this.daoCashier.save(cashier);
        }

        this.daoCashier.open(cashier);
    }
}
