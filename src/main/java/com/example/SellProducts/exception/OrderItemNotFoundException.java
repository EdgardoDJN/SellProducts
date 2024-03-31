package com.example.SellProducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(String message){
        super(message);
    }

    public OrderItemNotFoundException(){
        super("Order Item not found");
    }
}
