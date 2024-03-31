package com.example.SellProducts.dto.orderItem;

import jakarta.validation.constraints.NotNull;

public record OrderItemDto(
        Long id,
        Long productId,
        Long orderId,
        Integer quantity,
        Double price) { }
