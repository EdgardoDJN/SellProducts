package com.example.SellProducts.repositories;

import com.example.SellProducts.entities.Payment;
import com.example.SellProducts.entities.methodPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDatePaymentBetween(LocalDate start, LocalDate end);
    List<Payment> findByOrderIdAndMethod(Long orderId, methodPayment method);
}