package com.example.SellProducts.repositories;

import com.example.SellProducts.AbstractIntegrationDBTest;
import com.example.SellProducts.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerRepositoryTest extends AbstractIntegrationDBTest {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    void init() {
        Customer customer = Customer.builder()
                .name("Customer 1")
                .email("edgardodavid@gmail.com")
                .address("Calle 123")
                .build();
        customerRepository.save(customer);
        customerRepository.flush();
    }

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void givenCustomer_whenFindByEmail_thenReturnProduct() {
        // Given
        init();

        // When
        List<Customer> customers = customerRepository.findByEmail("edgardodavid@gmail.com");

        // Then
        assertEquals(1, customers.size());
    }

    @Test
    void givenCustomer_whenFindByEmail_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Customer> customers = customerRepository.findByEmail("prueba");

        // Then
        assertEquals(0, customers.size());
    }

    @Test
    void givenCustomer_whenFindByAddress_thenReturnProduct() {
        // Given
        init();

        // When
        List<Customer> customers = customerRepository.findByAddress("Calle 123");

        // Then
        assertEquals(1, customers.size());
    }

    @Test
    void givenCustomer_whenFindByAddress_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Customer> customers = customerRepository.findByAddress("prueba");

        // Then
        assertEquals(0, customers.size());
    }

    @Test
    void givenCustomer_whenFindByNameStartingWith_thenReturnProduct() {
        // Given
        init();

        // When
        List<Customer> customers = customerRepository.findByNameStartingWith("Customer");

        // Then
        assertEquals(1, customers.size());
    }

    @Test
    void givenCustomer_whenFindByNameStartingWith_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Customer> customers = customerRepository.findByNameStartingWith("prueba");

        // Then
        assertEquals(0, customers.size());
    }
}
