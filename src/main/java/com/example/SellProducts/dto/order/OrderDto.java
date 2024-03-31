package com.example.SellProducts.dto.order;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.SellProducts.dto.detailShipping.DetailShippingDto;
import com.example.SellProducts.dto.orderItem.OrderItemDto;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.entities.OrderStatus;

public record OrderDto(
        Long id,
        LocalDateTime dateOrder,
        OrderStatus status,
        Long customerId,
        List<OrderItemDto> orderItems,
        PaymentDTO payment,
        DetailShippingDto detailShipping
) {
    public List<OrderItemDto> orderItems(){
        return Collections.unmodifiableList(orderItems);
    }
}
