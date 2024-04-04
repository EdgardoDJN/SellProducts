package com.example.SellProducts.service;

import com.example.SellProducts.dto.payment.*;
import com.example.SellProducts.entities.methodPayment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    PaymentDTO2 getPaymentById(Long id);
    List<PaymentDTO2> getAllPayments();
    List<PaymentDTO2> getPaymentsByOrderId(Long orderId, methodPayment method);
    List<PaymentDTO2> getPaymentsForDateRange(LocalDate start, LocalDate end);
    PaymentDTO2 createPayment(CreatePaymentDTO createPaymentDTO);
    PaymentDTO2 UpdatePayment(Long id, UpdatePaymentDTO updatePaymentDTO);
    void deletePayment(Long id);
}