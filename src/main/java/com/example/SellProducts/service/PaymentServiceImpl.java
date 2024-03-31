package com.example.SellProducts.service;

import com.example.SellProducts.dto.payment.*;
import com.example.SellProducts.entities.Payment;
import com.example.SellProducts.entities.methodPayment;
import com.example.SellProducts.exception.OrderNotFoundException;
import com.example.SellProducts.exception.PaymentNotFoundException;
import com.example.SellProducts.repositories.OrderRepository;
import com.example.SellProducts.repositories.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentMapper paymentMapper;
    private final PaymentProvider paymentProvider;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(
            PaymentMapper paymentMapper,
            PaymentRepository paymentRepository,
            OrderRepository orderRepository,
            PaymentProvider paymentProvider) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.paymentProvider = paymentProvider;
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
        return paymentMapper.toDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        var payments = paymentRepository.findAll();

        return payments.stream()
                .map(paymentMapper::toDTO)
                .toList();
    }

    @Override
    public List<PaymentDTO> getPaymentsByOrderId(Long orderId, methodPayment method){
        var payments = paymentRepository.findByOrderIdAndMethod(orderId, method);

        return payments.stream()
                .map(paymentMapper::toDTO)
                .toList();
    }

    @Override
    public List<PaymentDTO> getPaymentsForDateRange(LocalDate start, LocalDate end) {
        var payments = paymentRepository.findByDatePaymentBetween(start, end);

        return payments.stream()
                .map(paymentMapper::toDTO)
                .toList();
    }

    @Override
    public PaymentDTO createPayment(CreatePaymentDTO createPaymentDTO) {
        var order = orderRepository.findById(createPaymentDTO.orderId())
                .orElseThrow(OrderNotFoundException::new);

        var newPayment = CreatePaymentDTO.builder()
                .orderId(createPaymentDTO.orderId())
                .datePayment(createPaymentDTO.datePayment())
                .method(createPaymentDTO.method())
                .order(order)
                .build();

        var payment = paymentMapper.toEntity(newPayment);
        paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }

    @Override
    public PaymentDTO UpdatePayment(Long id, UpdatePaymentDTO updatePaymentDTO) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setDatePayment(updatePaymentDTO.datePayment());
            payment.setMethod(updatePaymentDTO.method());
            payment.setOrder(orderRepository.findById(updatePaymentDTO.orderId()).orElseThrow(PaymentNotFoundException::new));
            payment.setTotalPayment(paymentProvider.calculateTotalItems(updatePaymentDTO.orderId()));
            paymentRepository.save(payment);
            return paymentMapper.toDTO(payment);
        }).orElseThrow(PaymentNotFoundException::new);
    }

    @Override
    public void deletePayment(Long id) {
        var payment = paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
        paymentRepository.delete(payment);
    }
}
