package com.example.SellProducts.dto.orderItem;

import com.example.SellProducts.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    OrderItem toEntity(CreateOrderItemDto createOrderItemDto);
}
