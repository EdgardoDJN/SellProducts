package com.example.SellProducts.dto.product;

import com.example.SellProducts.dto.orderItem.OrderItemDto;
import lombok.Builder;

@Builder
public record ProductDto( 
    Long id,
    String name,
    Double price,
    Integer stock,
    OrderItemDto orderItem
) {

}
