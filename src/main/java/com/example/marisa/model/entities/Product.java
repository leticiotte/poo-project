package com.example.marisa.model.entities;

public class Product {
    Integer id;
    String name;
    Float sellPrice;
    Float buyPrice;
    Integer quantity;
    String size;
    String facet;
    String category;
    Integer minimumStock;
    String creationDate;

    public Product(Integer id, String name, Float sellPrice, Float buyPrice, Integer quantity, String size, String facet, String category, Integer minimumStock, String creationDate) {
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

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
