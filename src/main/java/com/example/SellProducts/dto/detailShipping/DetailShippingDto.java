package com.example.SellProducts.dto.detailShipping;

import lombok.Builder;

@Builder
public record DetailShippingDto(
       Long id,
       Long orderId,
       Long productId,
       String address,
       String transporter,
       String trackingNumber
) { }
