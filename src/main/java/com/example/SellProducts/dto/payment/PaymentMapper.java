package com.example.SellProducts.dto.payment;

import com.example.SellProducts.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toEntity(CreatePaymentDTO createPaymentDTO);
    PaymentDTO toDTO(Payment payment);
}
