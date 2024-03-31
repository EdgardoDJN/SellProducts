package com.example.SellProducts.dto.detailShipping;

import com.example.SellProducts.entities.Order;
import com.example.SellProducts.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;

@Builder
public record DetailShippingDto(
    Long id,
    String address,
    String transporter,
    String trackingNumber,
    Long productId,
    Long orderId){
}
