package com.example.SellProducts.dto.customer;

import com.example.SellProducts.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerToSaveDto customerDto);
    CustomerDto toDto(Customer customer);
}
