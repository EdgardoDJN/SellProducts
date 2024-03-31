package com.example.SellProducts.dto.payment;

import com.example.SellProducts.entities.methodPayment;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Builder
public record UpdatePaymentDTO(
        @NotNull(message = "Is required")
        Long orderId,
        @NotNull(message = "Is required")
        LocalDate datePayment,
        @NotNull(message = "Is required")
        methodPayment method) {
}
