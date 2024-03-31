package com.example.SellProducts.service;

import com.example.SellProducts.dto.detailShipping.*;
import com.example.SellProducts.entities.*;
import com.example.SellProducts.exception.*;
import com.example.SellProducts.repositories.*;

import java.util.List;

public class DetailShippingServiceImpl implements DetailShippingService{

    private final DetailShippingRepository detailShippingRepository;
    private final DetailShippingMapper detailShippingMapper;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public DetailShippingServiceImpl(
            DetailShippingRepository detailShippingRepository,
            DetailShippingMapper detailShippingMapper,
            ProductRepository productRepository,
            OrderRepository orderRepository) {
        this.detailShippingRepository = detailShippingRepository;
        this.detailShippingMapper = detailShippingMapper;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public DetailShippingDto getDetailShippingById(Long id) {
        return detailShippingRepository.findById(id)
                .map(detailShippingMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<DetailShippingDto> getAllDetailShipping() {
        return detailShippingRepository.findAll()
                .stream()
                .map(detailShippingMapper::toDto)
                .toList();
    }

    @Override
    public List<DetailShippingDto> getDetailShippingByOrderId(Long orderId) {
        return detailShippingRepository.findByOrderId(orderId)
                .stream()
                .map(detailShippingMapper::toDto)
                .toList();
    }

    @Override
    public List<DetailShippingDto> getDetailShippingByCarrier(String carrier) {
        return detailShippingRepository.findByTransporter(carrier)
                .stream()
                .map(detailShippingMapper::toDto)
                .toList();
    }

    @Override
    public DetailShippingDto createDetailShipping(CreateShippingDto createShippingDto) {
        Product product = productRepository.findById(createShippingDto.productId())
                .orElseThrow(ProductNotFoundException::new);

        Order order = orderRepository.findById(createShippingDto.orderId())
                .orElseThrow(OrderNotFoundException::new);

        CreateShippingDto createShippingDtoWithEntities = CreateShippingDto.builder()
                .orderId(createShippingDto.orderId())
                .productId(createShippingDto.productId())
                .address(createShippingDto.address())
                .transporter(createShippingDto.transporter())
                .trackingNumber(createShippingDto.trackingNumber())
                .order(order)
                .product(product)
                .build();

        DetailShipping detailShipping = detailShippingMapper.toEntity(createShippingDtoWithEntities);
        var savedDetailShipping = detailShippingRepository.save(detailShipping);
        return detailShippingMapper.toDto(savedDetailShipping);
    }

    @Override
    public DetailShippingDto updateDetailShipping(Long id, UpdateShippingDto updateShippingDto) {
        DetailShipping detailShipping = detailShippingRepository.findById(id)
                .orElseThrow(DetailShippingNotFoundException::new);

        Product product = productRepository.findById(updateShippingDto.productId())
                .orElseThrow(ProductNotFoundException::new);

        Order order = orderRepository.findById(updateShippingDto.orderId())
                .orElseThrow(OrderNotFoundException::new);

        detailShipping.setAddress(updateShippingDto.address());
        detailShipping.setTransporter(updateShippingDto.transporter());
        detailShipping.setTrackingNumber(updateShippingDto.trackingNumber());
        detailShipping.setOrder(order);
        detailShipping.setProduct(product);

        var savedDetailShipping = detailShippingRepository.save(detailShipping);
        return detailShippingMapper.toDto(savedDetailShipping);
    }

    @Override
    public void deleteDetailShipping(Long id) {
        DetailShipping existsDetailShipping = detailShippingRepository.findById(id)
                .orElseThrow(DetailShippingNotFoundException::new);

        detailShippingRepository.delete(existsDetailShipping);
    }
}
