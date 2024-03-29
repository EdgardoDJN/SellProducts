package com.example.SellProducts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SellProducts.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByEmail(String email);
    List<Customer> findByAddress(String address);
    List<Customer> findByNameStartingWith(String nombre);
}