package com.example.marisa.model.usecases.cashier;

import com.example.marisa.model.entities.Cashier;
import com.example.marisa.persistence.dao.DAOCashier;

import java.util.Optional;

public class UCListCashier {
    private DAOCashier daoCashier;

    public UCListCashier(DAOCashier daoCashier) {
        this.daoCashier = daoCashier;
    }

    public Optional<Cashier> listCashier(Integer id) {
        return this.daoCashier.select(id);
    }
}
