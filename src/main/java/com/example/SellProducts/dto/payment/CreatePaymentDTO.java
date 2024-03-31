package com.example.SellProducts.dto.payment;

import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.methodPayment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreatePaymentDTO(
        @NotNull(message = "Is required")
        Long orderId,
        @JsonIgnore
        Order order,
        @NotNull(message = "Is required")
        LocalDate datePayment,
        @NotNull(message = "Is required")
        methodPayment method) { }
