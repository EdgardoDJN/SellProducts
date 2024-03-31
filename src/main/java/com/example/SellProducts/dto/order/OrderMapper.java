package com.example.SellProducts.dto.order;

import com.example.SellProducts.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderToSaveDto orderDto);
    OrderDtoRetrieve toDtoRetrieve(Order order);
}
