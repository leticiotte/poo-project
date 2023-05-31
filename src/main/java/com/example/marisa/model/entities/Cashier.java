package com.example.marisa.model.entities;

import com.example.marisa.model.enumeration.CashierStatusEnum;

public class Cashier {
    private int id;
    private float openingBalance;
    private float finalBalance;
    private CashierStatusEnum status;

    public Cashier(int id, float openingBalance, float finalBalance, String status) {
        this.id = id;
        this.openingBalance = openingBalance;
        this.finalBalance = finalBalance;
        this.status = CashierStatusEnum.valueOf(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(float openingBalance) {
        this.openingBalance = openingBalance;
    }

    public float getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(float finalBalance) {
        this.finalBalance = finalBalance;
    }

    public CashierStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CashierStatusEnum status) {
        this.status = status;
    }
}
