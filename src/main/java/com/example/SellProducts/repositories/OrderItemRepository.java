package com.example.SellProducts.repositories;

import com.example.SellProducts.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByProductName(String productName);
    @Query("SELECT SUM(oi.price) FROM OrderItem oi WHERE oi.product.id = :productId")
    Double totalSalesAProduct(Long productId);
}
