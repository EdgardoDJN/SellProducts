package com.example.SellProducts.dto.payment;

import com.example.SellProducts.dto.order.OrderDto;
import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.methodPayment;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PaymentDTO(
        Long id,
        Long orderId,
        Double totalPayment,
        LocalDate datePayment,
        methodPayment method) {
}
