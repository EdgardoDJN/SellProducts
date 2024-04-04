package com.example.SellProducts.service;

import com.example.SellProducts.dto.payment.*;
import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.dto.product.ProductToSaveDto2;
import com.example.SellProducts.entities.*;
import com.example.SellProducts.exception.PaymentNotFoundException;
import com.example.SellProducts.repositories.OrderRepository;
import com.example.SellProducts.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentMapper paymentMapper;
    @Mock
    private PaymentMapper2 paymentMapper2;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PaymentProvider paymentProvider;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private List<ProductToSaveDto> productsDto1;
    private List<ProductToSaveDto2> productsDto2;
    @BeforeEach
    void setUp() {
        var order = Order.builder()
                .id(1L)
                .status(OrderStatus.SHIPPED)
                .dateOrder(LocalDateTime.now())
                .build();

        List<Product> products = List.of(Product.builder()
                .id(1L)
                .name("Product")
                .price(100.0)
                .stock(10)
                .build());
        productsDto1 = List.of(ProductToSaveDto.builder()
                .name("Product")
                .price(100.0)
                .stock(10)
                .build());
        productsDto2 = List.of(ProductToSaveDto2.builder()
                .id(1L)
                .name("Product")
                .price(100.0)
                .stock(10)
                .build());

        payment = Payment.builder()
                .id(1L)
                .totalPayment(100.0)
                .datePayment(LocalDate.now())
                .method(methodPayment.CREDIT_CARD)
                .order(order)
                .products(products)
                .build();
    }

    @Test
    void givenPayment_whenGetById_thenReturnPayment() {
        // Given
        Long id = 1L;
        given(paymentRepository.findById(id)).willReturn(Optional.of(payment));
        given(paymentMapper.toDTO(payment)).willReturn(PaymentDTO2.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(payment.getOrder().getId())
                .products(productsDto2)
                .method(payment.getMethod())
                .datePayment(payment.getDatePayment())
                .build());

        // Then
        PaymentDTO2 found = paymentService.getPaymentById(id);

        // When
        assertThat(found).isNotNull();
        assertEquals(found.id(), payment.getId());
        assertEquals(found.totalPayment(), payment.getTotalPayment());
        assertEquals(found.orderId(), payment.getOrder().getId());
    }

    @Test
    void givenPayments_whenAllPayments_thenReturnAllPayments() {
        // Given
        given(paymentRepository.findAll()).willReturn(java.util.List.of(payment));
        given(paymentMapper.toDTO(payment)).willReturn(PaymentDTO2.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(payment.getOrder().getId())
                .products(productsDto2)
                .build());

        // Then
        var found = paymentService.getAllPayments();

        // When
        assertThat(found).isNotNull();
        assertEquals(found.size(), 1);
        assertEquals(found.get(0).id(), payment.getId());
        assertEquals(found.get(0).totalPayment(), payment.getTotalPayment());
        assertEquals(found.get(0).orderId(), payment.getOrder().getId());
    }

    @Test
    void givenPayments_whenGetByOrderId_thenReturnPayments() {
        // Given
        Long orderId = 1L;
        methodPayment method = methodPayment.CREDIT_CARD;
        given(paymentRepository.findByOrderIdAndMethod(orderId, method)).willReturn(java.util.List.of(payment));
        given(paymentMapper.toDTO(payment)).willReturn(PaymentDTO2.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(payment.getOrder().getId())
                .datePayment(payment.getDatePayment())
                .method(payment.getMethod())
                .products(productsDto2)
                .build());

        // Then
        var found = paymentService.getPaymentsByOrderId(orderId, method);

        // When
        assertThat(found).isNotNull();
        assertEquals(found.size(), 1);
        assertEquals(found.get(0).id(), payment.getId());
        assertEquals(found.get(0).totalPayment(), payment.getTotalPayment());
        assertEquals(found.get(0).orderId(), payment.getOrder().getId());
        assertEquals(found.get(0).method(), payment.getMethod());

    }

    @Test
    void givenPayments_whenGetByDateRange_thenReturnPayments() {
        // Given
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now();
        given(paymentRepository.findByDatePaymentBetween(start, end)).willReturn(java.util.List.of(payment));
        given(paymentMapper.toDTO(payment)).willReturn(PaymentDTO2.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(payment.getOrder().getId())
                .datePayment(payment.getDatePayment())
                .method(payment.getMethod())
                .products(productsDto2)
                .build());

        // Then
        var found = paymentService.getPaymentsForDateRange(start, end);

        // When
        assertThat(found).isNotNull();
        assertEquals(found.size(), 1);
        assertEquals(found.get(0).id(), payment.getId());
        assertEquals(found.get(0).totalPayment(), payment.getTotalPayment());
        assertEquals(found.get(0).orderId(), payment.getOrder().getId());
        assertEquals(found.get(0).datePayment(), payment.getDatePayment());
        assertEquals(found.get(0).method(), payment.getMethod());
    }

    @Test
    void givenPayment_whenCreatePayment_thenReturnCreatePayment() {
        // Given
        var createPaymentDTO = CreatePaymentDTO.builder()
                .orderId(payment.getOrder().getId())
                .datePayment(payment.getDatePayment())
                .method(payment.getMethod())
                .products(productsDto2)
                .build();

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(payment.getOrder()));
        when(paymentMapper.toEntity(any(CreatePaymentDTO.class))).thenReturn(payment);
        when(paymentMapper.toDTO(any(Payment.class))).thenReturn(PaymentDTO2.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(payment.getOrder().getId())
                .datePayment(payment.getDatePayment())
                .method(payment.getMethod())
                .products(productsDto2)
                .build());

        // Then
        var found = paymentService.createPayment(createPaymentDTO);

        // When
        assertThat(found).isNotNull();
        assertEquals(found.orderId(), payment.getOrder().getId());
        assertEquals(found.totalPayment(), payment.getTotalPayment());
        assertEquals(found.datePayment(), payment.getDatePayment());
        assertEquals(found.method(), payment.getMethod());
    }

    @Test
    void givenPayment_whenUpdatePayment_thenReturnUpdatedPayment() {
        // Given
        var updatePaymentDTO = UpdatePaymentDTO.builder()
                .orderId(payment.getOrder().getId())
                .datePayment(LocalDate.now())
                .method(methodPayment.CASH)
                .build();
        var paymentDTO2 = PaymentDTO2.builder()
                .id(payment.getId())
                .totalPayment(payment.getTotalPayment())
                .orderId(updatePaymentDTO.orderId())
                .datePayment(updatePaymentDTO.datePayment())
                .method(updatePaymentDTO.method())
                .products(productsDto2)
                .build();

        given(paymentRepository.findById(payment.getId())).willReturn(Optional.of(payment));
        given(orderRepository.findById(updatePaymentDTO.orderId())).willReturn(Optional.of(payment.getOrder()));
        given(paymentProvider.calculateTotalItems(updatePaymentDTO.orderId())).willReturn(payment.getTotalPayment());
        given(paymentMapper.toDTO(payment)).willReturn(paymentDTO2);
        given(paymentRepository.save(payment)).willReturn(payment);

        // Then
        var found = paymentService.UpdatePayment(payment.getId(), updatePaymentDTO);

        // When
        assertThat(found).isNotNull();
        assertEquals(found.id(), payment.getId());
        assertEquals(found.orderId(), updatePaymentDTO.orderId());
        assertEquals(found.datePayment(), updatePaymentDTO.datePayment());
        assertEquals(found.method(), updatePaymentDTO.method());
    }

    @Test
    void givenPayment_whenDeletePayment_thenVerify() {
        // Given
        Long id = 1L;
        given(paymentRepository.findById(id)).willReturn(Optional.of(payment));

        willDoNothing().given(paymentRepository).delete(payment);
        paymentService.deletePayment(id);

        verify(paymentRepository, times(1)).delete(payment);
    }

    @Test
    void givenPayment_whenDeletePayment_thenReturnException() {
        // Given
        given(paymentRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> paymentService.deletePayment(any()), "Payment not found");
    }
}