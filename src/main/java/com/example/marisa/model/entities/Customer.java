package com.example.marisa.model.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    Integer id;
    String name;
    String cpf;
    String phone;
    String email;
    String status;
    Integer number;
    String street;
    String complement;
    String city;
    String country;
    String zipcode;

    public Customer(Integer id, String name, String cpf, String phone, String email, String status, Integer number,
            String street, String complement, String city, String country, String zipcode) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.number = number;
        this.street = street;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    public void register(Customer customer) {

    }

    public void validateData(Customer customer) {

    }

    public void update(Customer customer) {

    }

    public boolean validateCPF(Customer customer) {

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
