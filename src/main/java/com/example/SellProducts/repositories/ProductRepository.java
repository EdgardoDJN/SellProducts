package com.example.SellProducts.repositories;

import com.example.SellProducts.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContainingIgnoreCase(String termSearch);
    List<Product> findByStockGreaterThan(int quantity);
    List<Product> findByPriceLessThanAndStockLessThan(double maxPrice, int maxStock);
}
