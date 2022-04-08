package com.example.g3s.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String s) {
        super(s);
    }
}

