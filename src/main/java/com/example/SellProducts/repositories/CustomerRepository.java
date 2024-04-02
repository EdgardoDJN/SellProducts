package com.example.SellProducts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SellProducts.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByEmail(String email);
    List<Customer> findByAddress(String address);
    //findByNameStartingWith
    List<Customer> findByNameIsContainingIgnoreCase(String nombre);
}
