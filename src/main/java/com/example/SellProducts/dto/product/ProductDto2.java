package com.example.SellProducts.dto.product;

import com.example.SellProducts.dto.orderItem.OrderItemDto;
import com.example.SellProducts.dto.payment.CreatePaymentDTO;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.dto.payment.PaymentDTO2;

import java.util.Collections;
import java.util.List;

public record ProductDto2(
        Long id,
        String name,
        Double price,
        Integer stock,
        List<OrderItemDto> orderItem,
        List<CreatePaymentDTO> payments
) {
    public List<OrderItemDto> orderItems(){
        return Collections.unmodifiableList(orderItem);

    }
}
