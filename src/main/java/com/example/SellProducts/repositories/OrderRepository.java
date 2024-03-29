package com.example.SellProducts.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.example.SellProducts.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SellProducts.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByDateOrderBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
    List<Order> findByCustomerIdAndStatus(Long customerId, OrderStatus status);
    @Query("SELECT DISTINCT p FROM Order p JOIN FETCH p.orderItems WHERE p.customer.id = :customerId")
    List<Order> retrieveOrdersWithItemsByCustomer(Long customerId);
}
