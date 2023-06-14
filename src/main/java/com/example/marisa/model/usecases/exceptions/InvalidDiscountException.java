package com.example.marisa.model.usecases.exceptions;

public class InvalidDiscountException extends RuntimeException{
    public InvalidDiscountException(String message) {
        super(message);
    }

}
