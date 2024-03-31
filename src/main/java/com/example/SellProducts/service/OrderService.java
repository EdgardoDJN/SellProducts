package com.example.SellProducts.service;

import com.example.SellProducts.dto.order.OrderDto;
import com.example.SellProducts.dto.order.OrderDtoRetrieve;
import com.example.SellProducts.dto.order.OrderToSaveDto;
import com.example.SellProducts.entities.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    OrderDto save(OrderToSaveDto orderDto);
    List<OrderDto> getOrdersByDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
    List<OrderDto> getOrdersByCustomerIdAndStatus(Long customerId, OrderStatus status);
    List<OrderDtoRetrieve> getOrdersByRetrieveOrdersWithItemsByCustomer(Long customerId);
    OrderDto getOrder(Long id);
    List<OrderDto> getOrders();
    OrderDto updateOrder(Long id, OrderToSaveDto orderDto);
    void deleteOrder(Long id);

}
