package com.example.SellProducts.dto.customer;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerToSaveDto(
        @NotNull(message = "Is required")
        String name,
        @NotNull(message = "Is required")
        String email,
        @NotNull(message = "Is required")
        String address
) {
    
}
