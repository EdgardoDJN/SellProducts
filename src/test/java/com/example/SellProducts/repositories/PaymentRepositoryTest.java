package com.example.SellProducts.repositories;

import com.example.SellProducts.AbstractIntegrationDBTest;
import com.example.SellProducts.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest extends AbstractIntegrationDBTest {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public PaymentRepositoryTest(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            CustomerRepository customerRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setUp() {
        paymentRepository.deleteAll();
        orderRepository.deleteAll();
    }

    List<Payment> init() {
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

        Payment payment = Payment.builder()
                .totalPayment(100.0)
                .datePayment(LocalDate.now())
                .method(methodPayment.CREDIT_CARD)
                .order(order)
                .build();

        paymentRepository.save(payment);

        return List.of(payment);
    }

    @Test
    void givenPayment_whenDatePaymentBetween_thenReturnListPayment() {
        // Given
        init();
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);

        // When
        List<Payment> payments = paymentRepository.findByDatePaymentBetween(start, end);

        // Then
        assertEquals(1, payments.size());
    }

    @Test
    void givenPayment_whenOrderIdAndMethod_thenReturnListPayment() {
        // Given
        var list = init();
        Long orderId = list.get(0).getOrder().getId();
        methodPayment method = list.get(0).getMethod();

        // When
        List<Payment> payments = paymentRepository.findByOrderIdAndMethod(orderId, method);

        // Then
        assertEquals(1, payments.size());
    }

    @Test
    void givenPayment_whenOrderIdAndMethodNotExists_thenReturnListPaymentEmpty() {
        // Given
        init();

        // When
        List<Payment> payments = paymentRepository.findByOrderIdAndMethod(1L, methodPayment.CASH);

        // Then
        assertEquals(0, payments.size());
    }
}