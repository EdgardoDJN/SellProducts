package com.example.SellProducts.dto.detailShipping;

import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

public record UpdateShippingDto(
        @NotNull(message = "Is required")
        Long orderId,
        @NotNull(message = "Is required")
        Long productId,
        @NotNull(message = "Is required")
        String address,
        @NotNull(message = "Is required")
        String transporter,
        @NotNull(message = "Is required")
        String trackingNumber
) { }
