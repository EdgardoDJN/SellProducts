package com.example.SellProducts.service;

import com.example.SellProducts.dto.payment.CreatePaymentDTO;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.dto.payment.PaymentMapper;
import com.example.SellProducts.dto.payment.UpdatePaymentDTO;
import com.example.SellProducts.entities.Payment;
import com.example.SellProducts.entities.methodPayment;
import com.example.SellProducts.exception.PaymentNotFoundException;
import com.example.SellProducts.repositories.OrderRepository;
import com.example.SellProducts.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(
            PaymentMapper paymentMapper,
            PaymentRepository paymentRepository,
            OrderRepository orderRepository) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
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
    public CreatePaymentDTO createPayment(CreatePaymentDTO createPaymentDTO) {
        var payment = paymentMapper.toEntity(createPaymentDTO);
        paymentRepository.save(payment);

        return createPaymentDTO;
    }

    @Override
    public PaymentDTO UpdatePayment(Long id, UpdatePaymentDTO updatePaymentDTO) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setTotalPayment(updatePaymentDTO.totalPayment());
            payment.setDatePayment(updatePaymentDTO.datePayment());
            payment.setMethod(updatePaymentDTO.method());
            payment.setOrder(orderRepository.findById(updatePaymentDTO.orderId()).orElseThrow(PaymentNotFoundException::new));
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
