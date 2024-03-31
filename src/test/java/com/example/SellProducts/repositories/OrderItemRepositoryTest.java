package com.example.SellProducts.repositories;

import com.example.SellProducts.AbstractIntegrationDBTest;
import com.example.SellProducts.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemRepositoryTest extends AbstractIntegrationDBTest {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderItemRepositoryTest(
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setUp() {
        orderItemRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
    }

    void init(){
        Product product1 = Product.builder()
                .name("Product 1")
                .price(100.0)
                .stock(10)
                .build();

        Product product2 = Product.builder()
                .name("Product 2")
                .price(200.0)
                .stock(20)
                .build();

        productRepository.saveAll(List.of(product1, product2));

        Customer customer = Customer.builder()
                .name("Customer 1")
                .email("prueba")
                .address("address")
                .build();

        customerRepository.save(customer);

        Order order = Order.builder()
                .dateOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .customer(customer)
                .build();

        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .price(100.0)
                .quantity(1)
                .product(product1)
                .order(order)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .price(200.0)
                .quantity(2)
                .product(product2)
                .order(order)
                .build();

        orderItemRepository.saveAll(List.of(orderItem, orderItem2));
    }
    @Test
    void givenOrderItems_whenOrderById_thenReturnListOrderItems() {
        // Given
        init();
        final Long orderId = 1L;

        // When
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        // Then
        assertEquals(2, orderItems.size());
    }

    @Test
    void givenOrderItems_whenOrderByIdNotExists_thenReturnListOrderItemsEmpty() {
        // Given
        final Long orderId = 1L;

        // When
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        // Then
        assertEquals(0, orderItems.size());
    }

    @Test
    void givenOrderItems_whenProductName_thenReturnListOrderItems() {
        // Given
        init();
        final String productName = "Product 1";

        // When
        List<OrderItem> orderItems = orderItemRepository.findByProductName(productName);

        // Then
        assertEquals(1, orderItems.size());
    }

    @Test
    void givenOrderItems_whenTotalSalesAProduct_thenReturnTotalSales() {
        // Given
        init();
        final Long productId = 1L;

        // When
        Double totalSales = orderItemRepository.totalSalesAProduct(productId);

        // Then
        assertEquals(100.0, totalSales);
    }

    @Test
    void givenOrderItems_whenProductId_thenReturnListOrderItems() {
        // Given
        init();
        final Long productId = 1L;

        // When
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);

        // Then
        assertEquals(1, orderItems.size());
    }
}