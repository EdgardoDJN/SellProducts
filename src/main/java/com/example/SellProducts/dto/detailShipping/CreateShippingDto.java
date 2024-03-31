package com.example.SellProducts.dto.detailShipping;

import com.example.SellProducts.entities.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateShippingDto(
        @NotNull(message = "Is required")
        Long orderId,
        @NotNull(message = "Is required")
        Long productId,
        @NotNull(message = "Is required")
        String address,
        @NotNull(message = "Is required")
        String transporter,
        @NotNull(message = "Is required")
        String trackingNumber,
        @JsonIgnore
        Order order,
        @JsonIgnore
        Product product
) { }
