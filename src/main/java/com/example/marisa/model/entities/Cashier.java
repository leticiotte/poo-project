package com.example.marisa.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private int id;
    private float openingBalance;
    private float finalBalance;
    private String status;

    public Cashier(int id, float openingBalance, float finalBalance, String status) {
        this.setId(id);
        this.setOpeningBalance(openingBalance);
        this.setFinalBalance(finalBalance);
        this.setStatus(status);
    }
    public void openCashier(){}

    public void closeCashier() {
    }
    public void calculateDaySales() {}

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
