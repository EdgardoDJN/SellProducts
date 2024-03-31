package com.example.SellProducts.dto.orderItem;

import com.example.SellProducts.entities.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateOrderItemDto(
        @NotNull(message = "Is required")
        Long productId,
        @NotNull(message = "Is required")
        Long orderId,
        @NotNull(message = "Is required")
        Integer quantity,
        @NotNull(message = "Is required")
        Double price,
        @JsonIgnore
        Order order,
        Product product) {
}
