package com.example.marisa.model.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    String name;
    String cpf;
    Address address;
    String phone;
    String email;
    String status;

    public Customer(String name, String cpf, Address address, String phone, String email, String status) {
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
