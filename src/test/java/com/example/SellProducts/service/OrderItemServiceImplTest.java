package com.example.SellProducts.service;

import com.example.SellProducts.dto.orderItem.*;
import com.example.SellProducts.entities.*;
import com.example.SellProducts.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceImplTest {

    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    OrderItemMapper orderItemMapper;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    private OrderItem orderItem;
    @BeforeEach
    void setUp() {
        var product = Product.builder()
                .id(1L)
                .name("Product 1")
                .price(100.0)
                .stock(10)
                .build();

        var order = Order.builder()
                .id(1L)
                .status(OrderStatus.SHIPPED)
                .dateOrder(LocalDateTime.now())
                .build();

        orderItem = OrderItem.builder()
                .id(1L)
                .product(product)
                .order(order)
                .quantity(1)
                .price(100.0)
                .build();
    }

    @Test
    void givenOrderItem_whenCreateOrderItem_thenReturnOrderItemDto() {
        var createOrderItemDto = CreateOrderItemDto.builder()
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .order(orderItem.getOrder())
                .product(orderItem.getProduct())
                .build();

        var orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        when(productRepository.findById(createOrderItemDto.productId())).thenReturn(java.util.Optional.of(orderItem.getProduct()));
        when(orderRepository.findById(createOrderItemDto.orderId())).thenReturn(java.util.Optional.of(orderItem.getOrder()));
        when(orderItemMapper.toEntity(createOrderItemDto)).thenReturn(orderItem);
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);
        when(orderItemMapper.toDto(orderItem)).thenReturn(orderItemDto);

        var result = orderItemService.createOrderItem(createOrderItemDto);

        assertEquals(orderItemDto, result);
    }

    @Test
    void givenOrderItem_whenUpdateOrderItem_thenReturnOrderItemDto() {
        var updateOrderItemDto = UpdateOrderItemDto.builder()
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        var orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        when(orderItemRepository.findById(orderItem.getId())).thenReturn(java.util.Optional.of(orderItem));
        when(orderRepository.findById(updateOrderItemDto.getOrderId())).thenReturn(java.util.Optional.of(orderItem.getOrder()));
        when(productRepository.findById(updateOrderItemDto.getProductId())).thenReturn(java.util.Optional.of(orderItem.getProduct()));
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);
        when(orderItemMapper.toDto(orderItem)).thenReturn(orderItemDto);

        var result = orderItemService.updateOrderItem(orderItem.getId(), updateOrderItemDto);

        assertEquals(orderItemDto, result);
    }

    @Test
    void givenOrderItem_whenDeleteOrderItem_thenVerifyDelete() {
        given(orderItemRepository.findById(orderItem.getId())).willReturn(java.util.Optional.of(orderItem));

        willDoNothing().given(orderItemRepository).delete(orderItem);
        orderItemService.deleteOrderItem(orderItem.getId());

        verify(orderItemRepository, times(1)).delete(orderItem);
    }

    @Test
    void givenOrderItem_whenGetOrderItemById_thenReturnOrderItemDto() {
        var orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        given(orderItemRepository.findById(orderItem.getId())).willReturn(java.util.Optional.of(orderItem));
        given(orderItemMapper.toDto(orderItem)).willReturn(orderItemDto);

        var result = orderItemService.getOrderItem(orderItem.getId());

        assertEquals(orderItemDto, result);
    }

    @Test
    void givenOrderItems_whenGetOrderItems_thenReturnListOrderItemDto() {
        var orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        given(orderItemRepository.findAll()).willReturn(java.util.List.of(orderItem));
        given(orderItemMapper.toDto(orderItem)).willReturn(orderItemDto);

        var result = orderItemService.getOrderItems();

        assertEquals(1, result.size());
        assertEquals(orderItemDto, result.get(0));
    }

    @Test
    void givenOrderItems_whenGetOrderItemsByOrderId_thenReturnListOrderItemDto() {
        var orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        given(orderItemRepository.findByOrderId(orderItem.getOrder().getId())).willReturn(java.util.List.of(orderItem));
        given(orderItemMapper.toDto(orderItem)).willReturn(orderItemDto);

        var result = orderItemService.getOrderItemsByOrderId(orderItem.getOrder().getId());

        assertEquals(1, result.size());
        assertEquals(orderItemDto, result.get(0));
    }

    @Test
    void givenOrderItems_whenGetOrderItemsByProductId_thenReturnListOrderItemDto() {
        var orderItemDto = OrderItemDto.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();

        given(orderItemRepository.findByProductId(orderItem.getProduct().getId())).willReturn(java.util.List.of(orderItem));
        given(orderItemMapper.toDto(orderItem)).willReturn(orderItemDto);

        var result = orderItemService.getOrderItemsByProductId(orderItem.getProduct().getId());

        assertEquals(1, result.size());
        assertEquals(orderItemDto, result.get(0));
    }
}