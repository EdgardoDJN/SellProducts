package com.example.SellProducts.dto.payment;

import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.dto.product.ProductToSaveDto2;
import com.example.SellProducts.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper2 {
    List<Product> toEntity(List<ProductToSaveDto> productDto);
    List<Product> toEntity2(List<ProductToSaveDto2> productDto);

}
