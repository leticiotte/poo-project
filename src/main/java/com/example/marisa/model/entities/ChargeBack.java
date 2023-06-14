package com.example.marisa.model.entities;

import java.time.LocalDate;
import java.util.List;

public class ChargeBack {
    private Integer id;
    private LocalDate date;
    private Sale sale;
    private List<Product> products;

    public ChargeBack(Integer id, LocalDate date, Sale sale, List<Product> products) {
        this.id = id;
        this.date = date;
        this.sale = sale;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
