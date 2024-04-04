package com.example.SellProducts.dto.payment;

import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.dto.product.ProductToSaveDto2;
import com.example.SellProducts.entities.methodPayment;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Builder
public record PaymentDTO2(
        Long id,
        Long orderId,
        Double totalPayment,
        LocalDate datePayment,
        methodPayment method,
        List<ProductToSaveDto2> products
) {
    public List<ProductToSaveDto2> product(){
        return Collections.unmodifiableList(products);}
}
