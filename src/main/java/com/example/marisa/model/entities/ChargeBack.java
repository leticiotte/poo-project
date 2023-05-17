package com.example.marisa.model.entities;

import java.util.Date;

public class ChargeBack {
    private int id;
    private Date date;
    private Sale sale;

    public ChargeBack(int id, Date date, Sale sale) {
        this.id = id;
        this.date = date;
        this.sale = sale;
    }

    public ChargeBack(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
