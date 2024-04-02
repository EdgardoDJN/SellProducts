package com.example.SellProducts.dto.order;

import com.example.SellProducts.dto.orderItem.OrderItemDto;
import com.example.SellProducts.dto.orderItem.UpdateOrderItemDto;
import com.example.SellProducts.entities.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class OrderDtoRetrieve {
    private Long id;
    private LocalDateTime dateOrder;
    private OrderStatus status;
    private Long customerId;
    private List<UpdateOrderItemDto> orderItems;
}