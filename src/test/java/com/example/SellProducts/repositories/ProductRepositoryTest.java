package com.example.SellProducts.repositories;

import com.example.SellProducts.AbstractIntegrationDBTest;
import com.example.SellProducts.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest extends AbstractIntegrationDBTest {
    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    void init() {
        Product product = Product.builder()
                .name("Product 1")
                .price(100.0)
                .stock(10)
                .build();

        productRepository.save(product);
        productRepository.flush();
    }

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void givenProduct_whenFindByNameContainingIgnoreCase_thenReturnProduct() {
        // Given
        init();

        // When
        List<Product> products = productRepository.findByNameContainingIgnoreCase("product");

        // Then
        assertEquals(1, products.size());
    }

    @Test
    void givenProduct_whenFindByNameContainingIgnoreCase_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Product> products = productRepository.findByNameContainingIgnoreCase("product2");

        // Then
        assertEquals(0, products.size());
    }

    @Test
    void givenProduct_whenFindByStockGreaterThan_thenReturnProduct() {
        // Given
        init();

        // When
        List<Product> products = productRepository.findByStockGreaterThan(5);

        // Then
        assertEquals(1, products.size());
    }

    @Test
    void givenProduct_whenFindByStockGreaterThan_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Product> products = productRepository.findByStockGreaterThan(20);

        // Then
        assertEquals(0, products.size());
    }

    @Test
    void givenProduct_whenFindByPriceLessThanAndStockLessThan_thenReturnProduct() {
        // Given
        init();

        // When
        List<Product> products = productRepository.findByPriceLessThanAndStockLessThan(200.0, 20);

        // Then
        assertEquals(1, products.size());
    }

    @Test
    void givenProduct_whenFindByPriceLessThanAndStockLessThan_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Product> products = productRepository.findByPriceLessThanAndStockLessThan(50.0, 5);

        // Then
        assertEquals(0, products.size());
    }
}
