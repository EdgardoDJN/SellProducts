package com.example.SellProducts.repositories;

import com.example.SellProducts.AbstractIntegrationDBTest;
import com.example.SellProducts.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DetailShippingRepositoryTest extends AbstractIntegrationDBTest {

    private final DetailShippingRepository detailShippingRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DetailShippingRepositoryTest(
            DetailShippingRepository detailShippingRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository) {
        this.detailShippingRepository = detailShippingRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setUp() {
        detailShippingRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
    }

    void init(){
        Product product = Product.builder()
                .name("Product 1")
                .price(100.0)
                .stock(10)
                .build();

        productRepository.save(product);

        Customer customer = Customer.builder()
                .name("Customer 1")
                .email("prueba")
                .address("address")
                .build();

        customerRepository.save(customer);

        Order order = Order.builder()
                .customer(customer)
                .dateOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .build();

        orderRepository.save(order);

        DetailShipping detailShipping = DetailShipping.builder()
                .order(order)
                .transporter("Transporter 1")
                .trackingNumber("123456")
                .build();

        detailShippingRepository.save(detailShipping);
    }
    @Test
    void givenDetailShipping_whenOrderIdExists_thenReturnListDetailShipping() {
        // Given
        init();

        // When
        var detailShipping = detailShippingRepository.findByOrderId(1L);

        // Then
        assertEquals(1, detailShipping.size());
    }

    @Test
    void givenDetailShipping_whenTransporterExists_thenReturnListDetailShipping() {
        // Given
        init();

        // When
        var detailShipping = detailShippingRepository.findByTransporter("Transporter 1");

        // Then
        assertEquals(1, detailShipping.size());
    }

    @Test
    void givenDetailShipping_whenOrderStatusExists_thenReturnListDetailShipping() {
        // Given
        init();

        // When
        var detailShipping = detailShippingRepository.findByOrderStatus(OrderStatus.PENDING);

        // Then
        assertEquals(1, detailShipping.size());
    }
}