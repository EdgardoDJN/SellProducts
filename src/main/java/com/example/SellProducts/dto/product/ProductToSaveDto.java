package com.example.SellProducts.dto.product;

import lombok.Builder;

@Builder
public record ProductToSaveDto(
        String name,
        Double price,
        Integer stock
) {
    
}
