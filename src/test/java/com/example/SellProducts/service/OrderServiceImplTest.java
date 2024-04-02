package com.example.SellProducts.service;

import com.example.SellProducts.dto.order.*;
import com.example.SellProducts.dto.orderItem.UpdateOrderItemDto;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.OrderStatus;
import com.example.SellProducts.exception.OrderNotFoundException;
import com.example.SellProducts.repositories.CustomerRepository;
import com.example.SellProducts.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private OrderMapper2 orderMapper2;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private OrderDtoRetrieve orderDtoRetrieve;
    private UpdateOrderItemDto updateOrderItemDto;
    private List<Object[]> simulatedOrders = new ArrayList<>();
    private static final String PENDING = "PENDING";

    @BeforeEach
    public void setUp() {
        Timestamp dateOrder = new Timestamp(System.currentTimeMillis());
        simulatedOrders.add(new Object[] {1L, dateOrder, "PENDING", 1L, 1L, 100.0, 4, 1L, 1L});
        updateOrderItemDto = UpdateOrderItemDto.builder()
                .productId(1L)
                .price(100.0)
                .orderId(1L)
                .quantity(1)
                .build();
        List<UpdateOrderItemDto> orderItems = List.of(updateOrderItemDto);
        // Convertir el Timestamp a LocalDateTime
        Instant instant = Instant.ofEpochMilli(dateOrder.getTime());
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        orderDtoRetrieve = OrderDtoRetrieve.builder()
                .id(1L)
                .dateOrder(localDateTime)
                .status(OrderStatus.PENDING)
                .customerId(1L)
                .orderItems(orderItems)
                .build();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer 1");
        customer.setEmail("eda@gmail.com");
        customer.setAddress("Address 1");

        order = new Order();
        order.setId(1L);
        order.setDateOrder(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(customer);
    }

    @Test
    void givenOrderId_whenGetOrderById_thenReturnOrderDto() {
        // Given
        Long orderId = 1L;
        given(orderRepository.findById(orderId)).willReturn(java.util.Optional.of(order));
        given(orderMapper.toDto(order)).willReturn(OrderDto.builder()
                .id(order.getId())
                .dateOrder(order.getDateOrder())
                .status(order.getStatus())
                .customerId(order.getCustomer().getId())
                .build());

        // When
        OrderDto orderDto = orderService.getOrder(orderId);

        // Then
        assertThat(orderDto.id()).isEqualTo(order.getId());
        assertThat(orderDto.dateOrder()).isEqualTo(order.getDateOrder());
        assertThat(orderDto.status()).isEqualTo(order.getStatus());
        assertThat(orderDto.customerId()).isEqualTo(order.getCustomer().getId());
    }

    @Test
    void givenOrderId_whenGetOrderById_thenThrowOrderNotFoundException() {
        // Given
        Long id = 1L;
        given(orderRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrder(id), "Order not found");
    }

    @Test
    void givenOrder_whenGetAllOrders_thenReturnListOfOrderDto() {
        // Given
        given(orderRepository.findAll()).willReturn(java.util.List.of(order));
        given(orderMapper.toDto(order)).willReturn(OrderDto.builder()
                .id(order.getId())
                .dateOrder(order.getDateOrder())
                .status(order.getStatus())
                .customerId(order.getCustomer().getId())
                .build());

        // When
        var orders = orderService.getOrders();

        // Then
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).id()).isEqualTo(order.getId());
        assertThat(orders.get(0).dateOrder()).isEqualTo(order.getDateOrder());
        assertThat(orders.get(0).status()).isEqualTo(order.getStatus());
        assertThat(orders.get(0).customerId()).isEqualTo(order.getCustomer().getId());
    }
    @Test
    void givenDates_whenGetOrdersByDateBetween_thenReturnListOfOrderDto() {
        // Given
        LocalDateTime dateStart = LocalDateTime.now().minusDays(1);
        LocalDateTime dateEnd = LocalDateTime.now();
        given(orderRepository.findByDateOrderBetween(dateStart, dateEnd)).willReturn(java.util.List.of(order));
        given(orderMapper.toDto(order)).willReturn(OrderDto.builder()
                .id(order.getId())
                .dateOrder(order.getDateOrder())
                .status(order.getStatus())
                .customerId(order.getCustomer().getId())
                .build());

        // When
        var orders = orderService.getOrdersByDateBetween(dateStart, dateEnd);

        // Then
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).id()).isEqualTo(order.getId());
        assertThat(orders.get(0).dateOrder()).isEqualTo(order.getDateOrder());
        assertThat(orders.get(0).status()).isEqualTo(order.getStatus());
        assertThat(orders.get(0).customerId()).isEqualTo(order.getCustomer().getId());
    }

    @Test
    void givenDates_whenGetOrdersByDateBetween_thenReturnEmptyList() {
        // Given
        LocalDateTime dateStart = LocalDateTime.now().minusDays(1);
        LocalDateTime dateEnd = LocalDateTime.now();
        given(orderRepository.findByDateOrderBetween(dateStart, dateEnd)).willReturn(java.util.List.of());

        // When
        var orders = orderService.getOrdersByDateBetween(dateStart, dateEnd);

        // Then
        assertThat(orders).isEmpty();
    }

    @Test
    void givenCustomerIdAndStatus_whenGetOrdersByCustomerIdAndStatus_thenReturnListOfOrderDto() {
        // Given
        Long customerId = 1L;
        OrderStatus status = OrderStatus.PENDING;
        given(orderRepository.findByCustomerIdAndStatus(customerId, status)).willReturn(java.util.List.of(order));
        given(orderMapper.toDto(order)).willReturn(OrderDto.builder()
                .id(order.getId())
                .dateOrder(order.getDateOrder())
                .status(order.getStatus())
                .customerId(order.getCustomer().getId())
                .build());

        // When
        var orders = orderService.getOrdersByCustomerIdAndStatus(customerId, status);

        // Then
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).id()).isEqualTo(order.getId());
        assertThat(orders.get(0).dateOrder()).isEqualTo(order.getDateOrder());
        assertThat(orders.get(0).status()).isEqualTo(order.getStatus());
        assertThat(orders.get(0).customerId()).isEqualTo(order.getCustomer().getId());
    }

    @Test
    void givenCustomerIdAndStatus_whenGetOrdersByCustomerIdAndStatus_thenReturnEmptyList() {
        // Given
        Long customerId = 1L;
        OrderStatus status = OrderStatus.PENDING;
        given(orderRepository.findByCustomerIdAndStatus(customerId, status)).willReturn(java.util.List.of());

        // When
        var orders = orderService.getOrdersByCustomerIdAndStatus(customerId, status);

        // Then
        assertThat(orders).isEmpty();
    }

    @Test
    void givenCustomerId_whenGetOrdersByRetrieveOrdersWithItemsByCustomer_thenReturnListOfOrderDtoRetrieve() {
        // Given
        Long customerId = 1L;
        given(orderRepository.retrieveOrdersWithItemsByCustomer(customerId)).willReturn(simulatedOrders);
        given(orderMapper2.toDto(simulatedOrders)).willReturn(java.util.List.of(orderDtoRetrieve));

        // When
        var orders = orderService.getOrdersByRetrieveOrdersWithItemsByCustomer(customerId);

        // Then
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getId()).isEqualTo(order.getId());
        assertThat(orders.get(0).getStatus()).isEqualTo(order.getStatus());
        assertThat(orders.get(0).getCustomerId()).isEqualTo(order.getCustomer().getId());
    }

    @Test
    void givenCustomerId_whenGetOrdersByRetrieveOrdersWithItemsByCustomer_thenReturnEmptyList() {
        // Given
        Long customerId = 1L;
        given(orderRepository.retrieveOrdersWithItemsByCustomer(customerId)).willReturn(java.util.List.of());

        // When
        var orders = orderService.getOrdersByRetrieveOrdersWithItemsByCustomer(customerId);

        // Then
        assertThat(orders).isEmpty();
    }

    @Test
    void givenOrder_whenUpdateOrder_thenReturnOrderDto() {
        // Given
        Long orderId = 1L;
        OrderDto orderDto = OrderDto.builder()
                .id(orderId)
                .dateOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .customerId(1L)
                .build();
        OrderToSaveDto orderDto2 = OrderToSaveDto.builder()
                .dateOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .idCustomer(1L)
                .build();
        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));
        given(customerRepository.findById(orderDto2.idCustomer())).willReturn(Optional.of(order.getCustomer()));
        given(orderRepository.save(order)).willReturn(order);
        given(orderMapper.toDto(order)).willReturn(orderDto);

        // When
        OrderDto updatedOrder = orderService.updateOrder(orderId, orderDto2);

        // Then
        assertThat(updatedOrder.id()).isEqualTo(orderId);
        assertThat(updatedOrder.dateOrder()).isEqualTo(order.getDateOrder());
        assertThat(updatedOrder.status()).isEqualTo(order.getStatus());
        assertThat(updatedOrder.customerId()).isEqualTo(order.getCustomer().getId());
    }

    @Test
    void givenOrderId_whenUpdateOrder_thenThrowOrderNotFoundException() {
        // Given
        Long orderId = 1L;
        OrderToSaveDto orderDto = OrderToSaveDto.builder()
                .dateOrder(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .idCustomer(1L)
                .build();
        given(orderRepository.findById(orderId)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(orderId, orderDto), "Order not found");
    }

    @Test
    void givenOrderId_whenDeleteOrder_thenOrderIsDeleted() {
        // Given
        Long orderId = 1L;
        given(orderRepository.findById(orderId)).willReturn(Optional.of(order));

        willDoNothing().given(orderRepository).delete(order);
        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).delete(order);

        // Then
    }

    @Test
    void givenOrderId_whenDeleteOrder_thenThrowOrderNotFoundException() {
        // Given
        Long orderId = 1L;
        given(orderRepository.findById(orderId)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(orderId), "Order not found");
    }

}
