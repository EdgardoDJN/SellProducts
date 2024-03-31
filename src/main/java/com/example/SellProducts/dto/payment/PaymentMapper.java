package com.example.SellProducts.dto.payment;

import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.Payment;
import com.example.SellProducts.repositories.OrderRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PaymentMapper {

    @Autowired
    private PaymentProvider paymentProvider;

    @Mapping(target = "totalPayment", source = "orderId", qualifiedByName = "calculateTotalPayment")
    @Mapping(target = "id", ignore = true)
    public abstract Payment toEntity(CreatePaymentDTO createPaymentDTO);

    @Mapping(target = "orderId", source = "order.id")
    public abstract PaymentDTO toDTO(Payment payment);

    @Named("calculateTotalPayment")
    public Double calculateTotalPayment(Long orderId){
        return paymentProvider.calculateTotalItems(orderId);
    }

}
