package com.example.SellProducts.dto.orderItem;

import lombok.Builder;

@Builder
public record OrderItemDto(
        Long id,
        Integer quantity,
        Double price
) {

}
