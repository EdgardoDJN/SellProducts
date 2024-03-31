package com.example.SellProducts.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.example.SellProducts.dto.order.OrderDtoRetrieve;
import com.example.SellProducts.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SellProducts.entities.Order;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByDateOrderBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
    List<Order> findByCustomerIdAndStatus(Long customerId,  OrderStatus status);
    @Query(value = "SELECT * FROM orders p JOIN order_items oi ON p.id = oi.order_id WHERE p.customer_id = :customerId", nativeQuery = true)
    List<OrderDtoRetrieve> retrieveOrdersWithItemsByCustomer(@Param("customerId") Long customerId);

}
