package com.example.SellProducts.dto.product;

import com.example.SellProducts.entities.Product;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductToSaveDto productDto);
    ProductDto toDto(Product product);
}
