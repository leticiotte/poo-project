package com.example.marisa.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private int id;
    private float openingBalance;
    private float finalBalance;
    private List<Sale> sales = new ArrayList<>();
    private String status;

    public void openCashier() {
    }

    public void closeChasier() {
    }

    public void calculateDaySales() {
    }
}
