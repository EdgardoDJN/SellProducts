package com.example.SellProducts.dto.payment;

import com.example.SellProducts.entities.methodPayment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record CreatePaymentDTO(
        Long orderId,
        Double totalPayment,
        LocalDate datePayment,
        methodPayment method) { }
