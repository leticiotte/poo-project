package com.example.marisa.model.entities;

public class SaleItem {
    private Product product;
    private Integer quantity;
    private double discount = 1;
    private double price;
    private double priceWithDiscount;

    public SaleItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public boolean isValidDiscount(){
        double priceWithDiscount = this.product.getSellPrice() * this.discount;
        return !(priceWithDiscount < this.product.getBuyPrice());
    }

    public void applyDiscount(){
        if(this.isValidDiscount()){
            this.price = this.quantity * this.product.getSellPrice();
            this.priceWithDiscount = (this.quantity * this.product.getSellPrice()) * this.discount;
        }
    }

}
