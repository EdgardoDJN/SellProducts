package com.example.SellProducts.service;

import com.example.SellProducts.dto.detailShipping.*;

import java.util.List;

public interface DetailShippingService {
    DetailShippingDto getDetailShippingById(Long id);
    List<DetailShippingDto> getAllDetailShipping();
    List<DetailShippingDto> getDetailShippingByOrderId(Long orderId);
    List<DetailShippingDto> getDetailShippingByCarrier(String carrier);
    DetailShippingDto createDetailShipping(CreateShippingDto createShippingDto);
    DetailShippingDto updateDetailShipping(Long id, UpdateShippingDto updateShippingDto);
    void deleteDetailShipping(Long id);
}
