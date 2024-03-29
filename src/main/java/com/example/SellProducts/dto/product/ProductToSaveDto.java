package com.example.SellProducts.dto.product;

public record ProductToSaveDto(
        Long id,
        String name,
        double price,
        int stock
) {
    
}
