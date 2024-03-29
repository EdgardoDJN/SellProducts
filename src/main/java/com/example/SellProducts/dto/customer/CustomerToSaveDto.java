package com.example.SellProducts.dto.customer;

public record CustomerToSaveDto(
        Long id,
        String name,
        String email,
        String address
) {
    
}
