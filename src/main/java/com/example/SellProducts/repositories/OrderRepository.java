package com.example.SellProducts.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SellProducts.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByDateOrderBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
    List<Order> findByCustomerIdAndStatus(Long customerId, String status);
    @Query("SELECT DISTINCT p FROM Order p JOIN FETCH p.orderItems WHERE p.customer.id = :CustomerId")
    List<Order> retrieveOrdersWithItemsByCustomer(Long customerId);
}
