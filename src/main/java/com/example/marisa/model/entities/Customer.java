package com.example.marisa.model.entities;

public class Customer {
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String status;
    private Integer number;
    private String street;
    private String complement;
    private String city;
    private String country;
    private String zipcode;

    public Customer() {
    }

    public Customer(String name, String cpf, String phone, String email, String status, Integer number,
                    String street, String complement, String city, String country, String zipcode) {
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

    public boolean validateCPF() {
        String cleanCPF = cpf.replaceAll("[^0-9]", "");

        if (cleanCPF.length() != 11) {
            return false;
        }

        if (cleanCPF.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(cleanCPF.charAt(i));
            sum += digit * (10 - i);
        }
        int remainder = sum % 11;
        int firstVerificationDigit = (remainder < 2) ? 0 : 11 - remainder;
        if (Character.getNumericValue(cleanCPF.charAt(9)) != firstVerificationDigit) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(cleanCPF.charAt(i));
            sum += digit * (11 - i);
        }
        remainder = sum % 11;
        int secondVerificationDigit = (remainder < 2) ? 0 : 11 - remainder;
        if (Character.getNumericValue(cleanCPF.charAt(10)) != secondVerificationDigit) {
            return false;
        }

        return true;
    }

    public boolean validateEmail() {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public boolean validatePhoneNumber() {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        String cleanPhoneNumber = phone.replaceAll("[^0-9]", "");

        if (cleanPhoneNumber.length() != 11) {
            return false;
        }

        return true;
    }
}
