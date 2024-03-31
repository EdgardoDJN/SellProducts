package com.example.SellProducts.dto.order;

import java.time.LocalDateTime;

import com.example.SellProducts.dto.customer.CustomerDto;
import com.example.SellProducts.dto.detailShipping.DetailShippingDto;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.entities.DetailShipping;
import com.example.SellProducts.entities.OrderStatus;
import com.example.SellProducts.entities.Payment;

public record OrderToSaveDto( 
    Long id,
    LocalDateTime dateOrder,
    OrderStatus status,
    CustomerDto customer,
    PaymentDTO payment,
    DetailShippingDto detailShipping) {
}
