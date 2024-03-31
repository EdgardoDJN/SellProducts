package com.example.SellProducts.dto.customer;

import lombok.Builder;

@Builder
public record CustomerToSaveDto(
        String name,
        String email,
        String address
) {
    
}
