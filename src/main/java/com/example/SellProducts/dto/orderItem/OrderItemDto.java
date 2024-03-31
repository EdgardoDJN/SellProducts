package com.example.SellProducts.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemDto(
        
        Long productId,
        Long orderId,
        Integer quantity,
        Double price) { }