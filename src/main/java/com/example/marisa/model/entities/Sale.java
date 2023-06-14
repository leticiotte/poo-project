package com.example.marisa.model.entities;

import com.example.marisa.model.enumeration.PaymentMethodTypeEnum;
import com.example.marisa.model.enumeration.SaleStatusEnum;

import java.time.LocalDateTime;
import java.util.*;

public class Sale {
    private Integer id;
    private Integer cashierId;
    private String customerCpf;
    private List<SaleItem> products;
    private LocalDateTime date;
    private double totalPayablePrice;
    private double totalDiscount;
    private PaymentMethodTypeEnum paymentType;
    private String nf;
    private SaleStatusEnum saleStatusEnum;

    public Sale(Integer cashierId, String customerCpf) {
        this.cashierId = cashierId;
        this.customerCpf = customerCpf;
        date = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }
    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public List<SaleItem> getProducts() {
        return products;
    }

    public void setProducts(List<SaleItem> products) {
        this.products = products;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTotalPayablePrice() {
        return totalPayablePrice;
    }

    public void setTotalPayablePrice(double totalPayablePrice) {
        this.totalPayablePrice = totalPayablePrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public PaymentMethodTypeEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethodTypeEnum paymentType) {
        this.paymentType = paymentType;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public SaleStatusEnum getSaleStatusEnum() {
        return saleStatusEnum;
    }

    public void setSaleStatusEnum(SaleStatusEnum saleStatusEnum) {
        this.saleStatusEnum = saleStatusEnum;
    }

    public void addProduct(SaleItem saleItem) throws Exception {
        if (saleItem.isValidDiscount()) {
            if (this.products != null) {
                this.products.add(saleItem);
                this.totalDiscount += saleItem.getDiscountValue();
                this.totalDiscount += saleItem.getPayablePrice();
            } else {
                this.products = Collections.singletonList(saleItem);
            }
        } else {
            throw new Exception("Produto não possui desconto válido.");
        }
    }
    public void closeSale(PaymentMethodTypeEnum paymentType) {
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
