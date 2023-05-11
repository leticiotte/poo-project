package com.example.marisa.model.entities;

public class Payment {
    private int id;
    private String type;
    private String status;

    public Payment(int id, String type, String status) {
        this.id = id;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
