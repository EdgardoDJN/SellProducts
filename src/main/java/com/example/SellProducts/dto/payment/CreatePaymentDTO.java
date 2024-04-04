package com.example.SellProducts.dto.payment;

import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.dto.product.ProductToSaveDto2;
import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.methodPayment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

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
        methodPayment method,
        @NotNull(message = "Is required")
        List<ProductToSaveDto2> products

) { }
