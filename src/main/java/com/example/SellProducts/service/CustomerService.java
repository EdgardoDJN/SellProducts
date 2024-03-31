package com.example.SellProducts.service;

import com.example.SellProducts.dto.customer.CustomerDto;
import com.example.SellProducts.dto.customer.CustomerToSaveDto;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerToSaveDto customerDto);
    List<CustomerDto> getCustomersByEmail(String email);
    List<CustomerDto> getCustomersByAddress(String address);
    List<CustomerDto> getCustomersByNameStartingWith(String name);

    CustomerDto findById(Long id);
    List<CustomerDto> findAll();
    void deleteCustomer(Long id);
    CustomerDto update(Long id, CustomerToSaveDto customerDto);
}
