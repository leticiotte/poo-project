package com.example.marisa.model.entities;

import com.example.marisa.model.enumeration.PaymentMethodTypeEnum;

import java.util.*;

public class Sale {
    private Integer id;
    private String nf;
    private List<SaleItem> products;
    private Date date = new Date();;
    private double totalDiscount;
    private double totalValue;
    private double totalPayablePrice;
    private PaymentMethodTypeEnum paymentType;
    private Integer clientId;
    private Integer idCashier;

    public Sale() {
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

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
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

    public void addProduct(SaleItem product) throws Exception {
        if (product.isValidDiscount()) {
            if (this.products != null) {
                this.products.add(product);
            } else {
                this.products = Collections.singletonList(product);
            }
        } else {
            throw new Exception("Produto não possui desconto válido.");
        }
    }

    public boolean isProductsStockValid() {
        for (SaleItem item : this.products) {
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                return false;
            }
        }
        return true;
    }

    public void closeSale(PaymentMethodTypeEnum paymentType) throws Exception {
        for (SaleItem item : this.products) {
            if (item.isValidDiscount()) {
                item.applyDiscount();
                this.totalDiscount += item.getPrice() - item.getPriceWithDiscount();
                this.totalValue += item.getPrice();
            } else
                throw new Exception("Desconto inválido para o item " + item.getProduct().getName());
        }
        this.totalPayablePrice = this.totalValue - this.totalDiscount;
        this.paymentType = paymentType;
        this.nf = generateNF();
    }

    private String generateNF() {
        StringBuilder nf = new StringBuilder("Produtos: \n");
        for (SaleItem item : this.products) {
            nf.append(item.getProduct().getName())
                    .append(": R$")
                    .append(String.format("%.2f", item.getProduct().getSellPrice()))
                    .append(" x ")
                    .append(item.getQuantity());

            if (item.getDiscount() != 1) {
                nf.append(" (desconto de ").append(item.getQuantity() * 100).append("%)\n");
            } else {
                nf.append("\n");
            }
        }
        nf.append("Valor total: R$");
        nf.append(String.format("%.2f", this.totalPayablePrice));
        nf.append("\n");
        nf.append("Desconto: R$");
        nf.append(String.format("%.2f", this.totalDiscount));
        nf.append("\n");
        nf.append("---\nValor a ser pago: R$");
        nf.append(String.format("%.2f", this.totalDiscount));

        return nf.toString();
    }
}
