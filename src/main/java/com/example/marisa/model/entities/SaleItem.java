package com.example.marisa.model.entities;

public class SaleItem {
    private Product product;
    private Integer quantity;
    private double discount = 1;
    private double payablePrice;
    private double discountValue;

    public SaleItem() {
    }

    public SaleItem(Product product, Integer quantity, double discount, double payablePrice, double discountValue) {
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.payablePrice = payablePrice;
        this.discountValue = discountValue;
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

    public double getPayablePrice() {
        return payablePrice;
    }

    public void setPayablePrice(double payablePrice) {
        this.payablePrice = payablePrice;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public boolean isValidDiscount(){
        double priceWithDiscount = (this.product.getSellPrice() * this.discount)/100;
        return !(priceWithDiscount < this.product.getBuyPrice());
    }

    public void calculatePriceAndDiscount() {
        double totalValueWithoutDiscount = product.getSellPrice() * quantity;
        this.discountValue = (totalValueWithoutDiscount * this.discount)/100;
        this.payablePrice = totalValueWithoutDiscount - discountValue;
    }
}
