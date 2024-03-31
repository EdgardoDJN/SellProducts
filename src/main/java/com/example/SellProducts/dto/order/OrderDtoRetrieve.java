package com.example.SellProducts.dto.order;

import com.example.SellProducts.dto.orderItem.OrderItemDto;
import com.example.SellProducts.dto.orderItem.UpdateOrderItemDto;
import com.example.SellProducts.entities.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Builder
public record OrderDtoRetrieve(
        Long id,
        LocalDateTime dateOrder,
        OrderStatus status,
        Long customerId,
        List<UpdateOrderItemDto> orderItems
) {
    public List<UpdateOrderItemDto> orderItems(){
        return Collections.unmodifiableList(orderItems);
    }
}
