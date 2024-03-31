package com.example.SellProducts.dto.customer;

import java.util.Collections;
import java.util.List;

import com.example.SellProducts.dto.order.OrderDto;
import lombok.Builder;

@Builder
public record CustomerDto(
    Long id,
    String name,
    String email,
    String address,
    List<OrderDto> orders
) {
    public List<OrderDto> orders(){
        return Collections.unmodifiableList(orders);
    }
}
