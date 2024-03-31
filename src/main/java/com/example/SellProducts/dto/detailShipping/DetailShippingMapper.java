package com.example.SellProducts.dto.detailShipping;

import com.example.SellProducts.entities.DetailShipping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailShippingMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    DetailShippingDto toDto(DetailShipping detailShipping);

    @Mapping(target = "id", ignore = true)
    DetailShipping toEntity(CreateShippingDto createShippingDto);
}
