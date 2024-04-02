package com.example.SellProducts.dto.order;

import com.example.SellProducts.dto.orderItem.UpdateOrderItemDto;
import com.example.SellProducts.entities.OrderStatus;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderMapper2 {
    public List<OrderDtoRetrieve> toDto(List<Object[]> prueba) {
        System.out.println(prueba.size());
        Map<Long, OrderDtoRetrieve> orderMap = new HashMap<>();
        prueba.forEach((o) -> {
            Long id = (Long) ((Object[]) o)[0];
            if(orderMap.containsKey(id)){
                List<UpdateOrderItemDto> orderItems2 = new ArrayList<>();
                orderItems2.add(
                        UpdateOrderItemDto.builder()
                                .price((Double) ((Object[]) o)[5])
                                .quantity((Integer) ((Object[]) o)[6])
                                .orderId(((Long) ((Object[]) o)[7]))
                                .productId(((Long) ((Object[]) o)[8]))
                                .build()
                );
                orderMap.get(id).getOrderItems().addAll(orderItems2);
            }else{
                List<UpdateOrderItemDto> orderItems1 = new ArrayList<>();
                Timestamp dateOrder = (Timestamp) ((Object[]) o)[1];
                // Convertir el Timestamp a LocalDateTime
                Instant instant = Instant.ofEpochMilli(dateOrder.getTime());
                LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                String status = (String) ((Object[]) o)[2];
                OrderStatus orderStatus = OrderStatus.valueOf(status);
                Long customerId = (Long) ((Object[]) o)[3];
                orderItems1.add(
                        UpdateOrderItemDto.builder()
                                .price((Double) ((Object[]) o)[5])
                                .quantity((Integer) ((Object[]) o)[6])
                                .orderId(((Long) ((Object[]) o)[7]))
                                .productId(((Long) ((Object[]) o)[8]))
                                .build()
                );
                OrderDtoRetrieve orderDtoRetrieve = OrderDtoRetrieve.builder()
                        .id(id)
                        .dateOrder(localDateTime)
                        .status(orderStatus)
                        .customerId(customerId)
                        .orderItems(orderItems1)
                        .build();
                orderMap.put(id, orderDtoRetrieve);
            }


        });
        return new ArrayList<>(orderMap.values());
    }
}
