package com.example.SellProducts.dto.order;

import com.example.SellProducts.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "customerId", source = "customer.id")
    OrderDto toDto(Order order);
    @Mapping(target = "customer.id", source = "idCustomer")
    Order toEntity(OrderToSaveDto orderDto);
    OrderDtoRetrieve toDtoRetrieve(Order order);
}
