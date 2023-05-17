package com.example.marisa.model.entities;

public class SaleItem {
    private Product product;
    private Integer quantity;
    private float discount;
    private float price;

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

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isValidDiscount(){
        float priceWithDiscount = this.product.getSellPrice() * this.discount;
        if (priceWithDiscount < this.product.getBuyPrice()){
            return false;
        }
        return true;
    }

    public void applyDiscount(){
        if(this.isValidDiscount()){
            this.price = (this.quantity * this.product.getSellPrice()) * this.discount;
        }
    }

}
