package com.example.SellProducts.dto.order;

import java.time.LocalDateTime;

import com.example.SellProducts.dto.customer.CustomerDto;
import com.example.SellProducts.dto.customer.CustomerToSaveDto;
import com.example.SellProducts.dto.detailShipping.DetailShippingDto;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.entities.DetailShipping;
import com.example.SellProducts.entities.OrderStatus;
import com.example.SellProducts.entities.Payment;
import lombok.Builder;

@Builder
public record OrderToSaveDto( 
    LocalDateTime dateOrder,
    OrderStatus status,
    Long idCustomer) {
}
