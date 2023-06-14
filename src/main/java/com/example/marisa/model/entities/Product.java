package com.example.marisa.model.entities;

import java.time.LocalDate;

public class Product {
    private Integer id;
    private String name;
    private double sellPrice;
    private double buyPrice;
    private Integer quantity;
    private String size;
    private String facet;
    private String category;
    private Integer minimumStock;
    private LocalDate creationDate;

    public Product() {
    }

    public Product(Integer id, String name, double sellPrice, double buyPrice, Integer quantity, String size,
                   String facet, String category, Integer minimumStock, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.size = size;
        this.facet = facet;
        this.category = category;
        this.minimumStock = minimumStock;
        this.creationDate = creationDate;
    }

    public Boolean validateFields() {
        return this.name != null && this.category != null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFacet() {
        return facet;
    }

    public void setFacet(String facet) {
        this.facet = facet;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
