package com.example.SellProducts.service;

import com.example.SellProducts.dto.order.OrderDto;
import com.example.SellProducts.dto.order.OrderDtoRetrieve;
import com.example.SellProducts.dto.order.OrderMapper;
import com.example.SellProducts.dto.order.OrderToSaveDto;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.OrderStatus;
import com.example.SellProducts.exception.CustomerNotFoundException;
import com.example.SellProducts.exception.OrderNotFoundException;
import com.example.SellProducts.exception.ProductNotFoundException;
import com.example.SellProducts.repositories.CustomerRepository;
import com.example.SellProducts.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderDto save(OrderToSaveDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getOrdersByDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        var orders = orderRepository.findByDateOrderBetween(dateStart, dateEnd);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> getOrdersByCustomerIdAndStatus(Long customerId, OrderStatus status) {
        var orders = orderRepository.findByCustomerIdAndStatus(customerId, status);
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    //Revisar esta en los test y pendiente para mas adelante en los controladores
    @Override
    public List<OrderDtoRetrieve> getOrdersByRetrieveOrdersWithItemsByCustomer(Long customerId) {
        var orders = orderRepository.retrieveOrdersWithItemsByCustomer(customerId);
        return orders;
    }

    @Override
    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        var orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto updateOrder(Long id, OrderToSaveDto orderDto) {
        Order order2 = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        Customer customer = customerRepository.findById(orderDto.idCustomer()).orElseThrow(CustomerNotFoundException::new);
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomer(customer);
                    order.setDateOrder(orderDto.dateOrder());
                    order.setStatus(orderDto.status());
                    orderRepository.save(order);
                    return orderMapper.toDto(order);
                })
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public void deleteOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        orderRepository.delete(order);
    }
}
