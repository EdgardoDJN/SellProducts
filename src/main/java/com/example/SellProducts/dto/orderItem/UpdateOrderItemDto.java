package com.example.SellProducts.dto.orderItem;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderItemDto(
        @NotNull(message = "Is required")
        Long productId,
        @NotNull(message = "Is required")
        Long orderId,
        @NotNull(message = "Is required")
        Integer quantity,
        @NotNull(message = "Is required")
        Double price) {
}
