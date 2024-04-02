package com.example.SellProducts.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UpdateOrderItem2 {

    private Long productId;
    private Long orderId;
    private Integer quantity;
    private Double price;
}
