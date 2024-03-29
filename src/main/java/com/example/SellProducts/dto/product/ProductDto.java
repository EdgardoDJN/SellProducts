package com.example.SellProducts.dto.product;

import java.util.Collections;
import java.util.List;

import com.example.SellProducts.dto.orderItem.OrderItemDto;

public record ProductDto( 
    Long id,
    String name,
    double price,
    int stock,
    List<OrderItemDto> orderItems
) {
    public List<OrderItemDto> orderItemDtos(){
        return Collections.unmodifiableList(orderItems);
    }
}
