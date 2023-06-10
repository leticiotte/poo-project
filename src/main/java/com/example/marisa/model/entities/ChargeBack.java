package com.example.marisa.model.entities;

import java.time.LocalDate;

public class ChargeBack {
    private Integer id;
    private LocalDate date;
    private Sale sale;

    public ChargeBack(Integer id, LocalDate date, Sale sale) {
        this.id = id;
        this.date = date;
        this.sale = sale;
    }

    public ChargeBack(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
