package com.example.SellProducts.dto.product;

import com.example.SellProducts.dto.orderItem.OrderItemDto;
import com.example.SellProducts.entities.Product;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public record ProductDto(
        Long id,
        String name,
        Double price,
        Integer stock,
        List<OrderItemDto> orderItem
) {
    public List<OrderItemDto> orderItems(){
        return Collections.unmodifiableList(orderItem);
    }
}
