package com.example.SellProducts.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateOrderItemDto{
        @NotNull(message = "Is required")
        private Long productId;
        @NotNull(message = "Is required")
        private Long orderId;
        @NotNull(message = "Is required")
        private Integer quantity;
        @NotNull(message = "Is required")
        private Double price;
}
