package com.example.SellProducts.dto.payment;

import com.example.SellProducts.entities.OrderItem;
import com.example.SellProducts.repositories.OrderItemRepository;
import com.example.SellProducts.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentProvider {
    private final OrderItemRepository orderItemRepository;

    public PaymentProvider(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    public double calculateTotalItems(Long orderId){
        return orderItemRepository.findByOrderId(orderId)
                .stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }
}
