package com.example.SellProducts.exception;

public class DetailShippingNotFoundException extends RuntimeException{
    public DetailShippingNotFoundException(String message) {
        super(message);
    }

    public DetailShippingNotFoundException() {
        super("Detail Shipping not found");
    }
}
