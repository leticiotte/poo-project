package com.example.marisa.model.entities;

public class Address {
    Integer id;
    Integer number;
    String street;
    String complement;
    String city;
    String country;
    String zipcode;

    public Address(Integer id, Integer number, String street, String complement, String city, String country, String zipcode) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
