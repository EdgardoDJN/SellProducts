package com.example.SellProducts.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductToSaveDto2(
        @NotNull(message = "Is required")
        Long id,
        @NotNull(message = "Is required")
        String name,
        @NotNull(message = "Is required")
        Double price,
        @NotNull(message = "Is required")
        Integer stock
) {
}
