package com.example.SellProducts.service;

import com.example.SellProducts.dto.customer.CustomerDto;
import com.example.SellProducts.dto.customer.CustomerMapper;
import com.example.SellProducts.dto.customer.CustomerToSaveDto;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.exception.CustomerNotFoundException;
import com.example.SellProducts.repositories.CustomerRepository;
import com.example.SellProducts.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper
                               ) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto save(CustomerToSaveDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDto> getCustomersByEmail(String email) {
        var customers = customerRepository.findByEmail(email);
        return customers.stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public List<CustomerDto> getCustomersByAddress(String address) {
        var customers = customerRepository.findByAddress(address);
        return customers.stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public List<CustomerDto> getCustomersByNameStartingWith(String name) {
        var customers = customerRepository.findByNameIsContainingIgnoreCase(name);
        return customers.stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerDto findById(Long id) {
        var customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDto> findAll() {
        var customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        var customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDto update(Long id, CustomerToSaveDto customerDto) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerDto.name());
            customer.setAddress(customerDto.address());
            customer.setEmail(customerDto.email());
            customerRepository.save(customer);
            return customerMapper.toDto(customer);
        }).orElseThrow(CustomerNotFoundException::new);
    }
}
