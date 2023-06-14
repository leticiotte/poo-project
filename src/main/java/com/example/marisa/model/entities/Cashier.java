package com.example.marisa.model.entities;

import com.example.marisa.model.enumeration.CashierStatusEnum;

public class Cashier {
    private Integer id;
    private double openingBalance;
    private double finalBalance;
    private CashierStatusEnum status;

    public Cashier(Integer id, double openingBalance, double finalBalance, String status) {
        this.id = id;
        this.openingBalance = openingBalance;
        this.finalBalance = finalBalance;
        this.status = CashierStatusEnum.valueOf(status);
    }

    public Cashier(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public CashierStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CashierStatusEnum status) {
        this.status = status;
    }
}
