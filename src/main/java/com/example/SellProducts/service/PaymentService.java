package com.example.SellProducts.service;

import com.example.SellProducts.dto.payment.*;
import com.example.SellProducts.entities.methodPayment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
    List<PaymentDTO> getPaymentsByOrderId(Long orderId, methodPayment method);
    List<PaymentDTO> getPaymentsForDateRange(LocalDate start, LocalDate end);
    CreatePaymentDTO createPayment(CreatePaymentDTO createPaymentDTO);
    PaymentDTO UpdatePayment(Long id, UpdatePaymentDTO updatePaymentDTO);
    void deletePayment(Long id);
}