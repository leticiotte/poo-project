package com.example.marisa.model.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegister {
    Customer customer;
    String status;

    public CustomerRegister(Customer customer, String status) {
        this.customer = customer;
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void register(Customer customer){

    }

    public void validateData(Customer customer){

    }

    public void update(Customer customer){

    }

    public boolean validateCPF(Customer customer){

        String regex = "^(\\d{3}\\.){2}\\d{3}-\\d{2}$|\\d{11}$";

        String cpf = customer.getCpf();

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) {
            return false;
        }

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < 9; i++) {
            count += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int rest = 11 - count % 11;
        int firstDigit = (rest >= 10) ? 0 : rest;
        if (firstDigit != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        count = 0;
        for (int i = 0; i < 10; i++) {
            count += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        rest = 11 - count % 11;
        int secondDigit = (rest >= 10) ? 0 : rest;
        if (secondDigit != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        return true;
    }

    public void inactivate(Customer customer) {
        this.status = "INACTIVATE";
    }
}
