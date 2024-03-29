package com.example.SellProducts.repositories;

import com.example.SellProducts.entities.DetailShipping;
import com.example.SellProducts.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailShippingRepository extends JpaRepository<DetailShipping, Long> {
    List<DetailShipping> findByOrderId(Long orderId);
    List<DetailShipping> findByTransporter(String transporter);
    List<DetailShipping> findByOrderStatus(OrderStatus status);
}
