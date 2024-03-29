package com.example.SellProducts.dto.order;

import java.time.LocalDateTime;

import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.entities.DetailShipping;
import com.example.SellProducts.entities.OrderStatus;
import com.example.SellProducts.entities.Payment;

public record OrderToSaveDto( 
    Long id,
    LocalDateTime dateOrder,
    OrderStatus status,
    Customer customer,
    Payment payment,
    DetailShipping detailShipping) {
}
