package com.example.marisa.model.entities;

import com.example.marisa.model.enumeration.PaymentMethodTypeEnum;

import java.util.*;

public class Sale {
    private Integer id;
    private String nf;
    private List<SaleItem> products;
    private Date date;
    private float totalDiscount;
    private float totalValue;
    private PaymentMethodTypeEnum paymentType;
    private Integer clientId;
    private Integer idCashier;

    public Sale() {
    }

    public Sale(Integer id, String nf, List<SaleItem> products, Date date, float totalDiscount, float totalValue,
                PaymentMethodTypeEnum paymentType, Integer clientId) {
        this.id = id;
        this.nf = nf;
        this.products = products;
        this.date = date;
        this.totalDiscount = totalDiscount;
        this.totalValue = totalValue;
        this.paymentType = paymentType;
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public List<SaleItem> getProducts() {
        return products;
    }

    public void setProducts(List<SaleItem> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(float totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public PaymentMethodTypeEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethodTypeEnum paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void addProduct(SaleItem product){
        if (this.products != null) {
            this.products.add(product);
        }else{
            this.products = Collections.singletonList(product);
        }
    }

    public boolean isProductsStockValid(){
        for (SaleItem item : this.products) {
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                return false;
            }
        }
        return true;
    }

    public void closeSale(PaymentMethodTypeEnum paymentType){
        for (SaleItem item : this.products) {
            if (item.isValidDiscount()) item.applyDiscount();
            else throw new Error("Desconto inválido para o item " + item.getProduct().getName());
        }
        this.paymentType = paymentType;
    }
}
