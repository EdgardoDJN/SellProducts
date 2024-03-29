package com.example.SellProducts.dto.order;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.SellProducts.dto.orderItem.OrderItemDto;
import com.example.SellProducts.entities.OrderStatus;

public record OrderDto(
        Long id,
        LocalDateTime dateOrder,
        OrderStatus status,
        List<OrderItemDto> orderItems
) {
    public List<OrderItemDto> orderItems(){
        return Collections.unmodifiableList(orderItems);
    }
}
