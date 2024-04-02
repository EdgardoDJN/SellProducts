package com.example.SellProducts.repositories;

import com.example.SellProducts.AbstractIntegrationDBTest;
import com.example.SellProducts.dto.order.OrderDtoRetrieve;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.OrderItem;
import com.example.SellProducts.entities.Product;
import com.example.SellProducts.entities.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderRepositoryTest extends AbstractIntegrationDBTest {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository, CustomerRepository customerRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    List<Order> init() {
        Customer customer = Customer.builder()
                .name("Customer 1")
                .email("prueba")
                .address("address")
                .build();

        customerRepository.save(customer);

        Product product = Product.builder()
                .name("Product 1")
                .price(100.0)
                .stock(10)
                .build();
        productRepository.save(product);

        Order order = Order.builder()
                .dateOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .customer(customer)
                .build();
        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .quantity(1)
                .price(100.0)
                .order(order)
                .product(product)
                .build();
        orderItemRepository.save(orderItem);
        return List.of(order);
    }

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        orderItemRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void givenOrder_whenfindByDateOrderBetween_thenReturnOrder() {
        // Given
        init();

        // When
        List<Order> orders = orderRepository.findByDateOrderBetween(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));

        // Then
        assertEquals(1, orders.size());
    }

    @Test
    void givenOrder_whenfindByDateOrderBetween_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Order> orders = orderRepository.findByDateOrderBetween(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1));

        // Then
        assertEquals(0, orders.size());
    }

    @Test
    void givenOrder_whenfindByCustomerIdAndStatus_thenReturnOrder() {
        // Given
        var list = init();
        long id = list.get(0).getId();
        OrderStatus status = list.get(0).getStatus();

        // When
        List<Order> orders = orderRepository.findByCustomerIdAndStatus(id, status);

        // Then
        assertEquals(1, orders.size());
    }

    @Test
    void givenOrder_whenfindByCustomerIdAndStatus_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Order> orders = orderRepository.findByCustomerIdAndStatus(1L, OrderStatus.DELIVERED);

        // Then
        assertEquals(0, orders.size());
    }

    @Test
    void givenOrder_whenRetrieveOrdersWithItemsByCustomer_thenReturnOrder() {
        // Given
        var list = init();
        long id = list.get(0).getCustomer().getId();


        // When
        List<Object[]> orders = orderRepository.retrieveOrdersWithItemsByCustomer(id);

        // Then
        assertEquals(1, orders.size());
    }

    @Test
    void givenOrder_whenRetrieveOrdersWithItemsByCustomer_thenReturnEmpty() {
        // Given
        init();

        // When
        List<Object[]> orders = orderRepository.retrieveOrdersWithItemsByCustomer(2L);

        // Then
        assertEquals(0, orders.size());
    }


}
