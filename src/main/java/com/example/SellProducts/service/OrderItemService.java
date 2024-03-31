package com.example.SellProducts.service;

import com.example.SellProducts.dto.orderItem.*;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(CreateOrderItemDto createOrderItemDto);
    OrderItemDto updateOrderItem(Long id, UpdateOrderItemDto updateOrderItemDto);
    void deleteOrderItem(Long id);
    OrderItemDto getOrderItem(Long id);
    List<OrderItemDto> getOrderItems();
    List<OrderItemDto> getOrderItemsByOrderId(Long orderId);
    List<OrderItemDto> getOrderItemsByProductId(Long productId);
}
