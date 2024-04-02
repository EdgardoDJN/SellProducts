package com.example.SellProducts.service;

import com.example.SellProducts.dto.orderItem.*;
import com.example.SellProducts.entities.*;
import com.example.SellProducts.exception.*;
import com.example.SellProducts.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository,
            OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemMapper = orderItemMapper;
    }
    @Override
    public OrderItemDto createOrderItem(CreateOrderItemDto createOrderItemDto) {
        Product product = productRepository.findById(createOrderItemDto.productId())
                .orElseThrow(ProductNotFoundException::new);

        Order order = orderRepository.findById(createOrderItemDto.orderId())
                .orElseThrow(OrderNotFoundException::new);

        CreateOrderItemDto orderItemDto = CreateOrderItemDto.builder()
                .product(product)
                .order(order)
                .orderId(createOrderItemDto.orderId())
                .productId(createOrderItemDto.productId())
                .quantity(createOrderItemDto.quantity())
                .price(createOrderItemDto.price())
                .build();

        OrderItem orderItem = orderItemRepository.save(orderItemMapper.toEntity(orderItemDto));

        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderItemDto updateOrderItem(Long id, UpdateOrderItemDto updateOrderItemDto) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(OrderItemNotFoundException::new);

        orderItem.setQuantity(updateOrderItemDto.getQuantity());
        orderItem.setPrice(updateOrderItemDto.getPrice());
        orderItem.setOrder(orderRepository.findById(updateOrderItemDto.getOrderId()).orElseThrow(OrderNotFoundException::new));
        orderItem.setProduct(productRepository.findById(updateOrderItemDto.getProductId()).orElseThrow(ProductNotFoundException::new));

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);

        return orderItemMapper.toDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(OrderItemNotFoundException::new);

        orderItemRepository.delete(orderItem);
    }

    @Override
    public OrderItemDto getOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(OrderItemNotFoundException::new);

        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemDto> getOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderItemDto> getOrderItemsByProductId(Long productId) {
        return orderItemRepository.findByProductId(productId).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }
}
