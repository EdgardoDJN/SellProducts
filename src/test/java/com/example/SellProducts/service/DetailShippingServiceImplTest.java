package com.example.SellProducts.service;

import com.example.SellProducts.dto.detailShipping.*;
import com.example.SellProducts.entities.*;
import com.example.SellProducts.exception.DetailShippingNotFoundException;
import com.example.SellProducts.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DetailShippingServiceImplTest {

    @InjectMocks
    private DetailShippingServiceImpl detailShippingService;

    @Mock
    private DetailShippingRepository detailShippingRepository;
    @Mock
    private DetailShippingMapper detailShippingMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;

    private DetailShipping detailShipping;
    @BeforeEach
    void setUp() {
        var product = Product.builder()
                .id(1L)
                .name("Product 1")
                .price(100.0)
                .stock(10)
                .build();

        var order = Order.builder()
                .id(1L)
                .status(OrderStatus.SHIPPED)
                .dateOrder(LocalDateTime.now())
                .build();

        detailShipping = DetailShipping.builder()
                .id(1L)
                .product(product)
                .order(order)
                .trackingNumber("123456")
                .transporter("Carrier")
                .build();
    }

    @Test
    void givenDetailShippingId_whenGetDetailShippingById_thenReturnDetailShipping() {
        // given
        given(detailShippingRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping));
        given(detailShippingMapper.toDto(detailShipping)).willReturn(DetailShippingDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .trackingNumber("123456")
                .transporter("Carrier")
                .build());

        // when
        var result = detailShippingService.getDetailShippingById(1L);

        // then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1L, result.orderId());
        assertEquals(1L, result.productId());
        assertEquals("123456", result.trackingNumber());
        assertEquals("Carrier", result.transporter());
    }

    @Test
    void givenDetailShippingList_whenGetAllDetailShipping_thenReturnDetailShippingList() {
        // given
        given(detailShippingRepository.findAll()).willReturn(java.util.List.of(detailShipping));
        given(detailShippingMapper.toDto(detailShipping)).willReturn(DetailShippingDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .trackingNumber("123456")
                .transporter("Carrier")
                .build());

        // when
        var result = detailShippingService.getAllDetailShipping();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(1L, result.get(0).orderId());
        assertEquals(1L, result.get(0).productId());
        assertEquals("123456", result.get(0).trackingNumber());
        assertEquals("Carrier", result.get(0).transporter());
    }

    @Test
    void givenOrderId_whenGetDetailShippingByOrderId_thenReturnDetailShippingList() {
        // given
        given(detailShippingRepository.findByOrderId(1L)).willReturn(java.util.List.of(detailShipping));
        given(detailShippingMapper.toDto(detailShipping)).willReturn(DetailShippingDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .trackingNumber("123456")
                .transporter("Carrier")
                .build());

        // when
        var result = detailShippingService.getDetailShippingByOrderId(1L);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(1L, result.get(0).orderId());
        assertEquals(1L, result.get(0).productId());
        assertEquals("123456", result.get(0).trackingNumber());
        assertEquals("Carrier", result.get(0).transporter());
    }

    @Test
    void givenCarrier_whenGetDetailShippingByCarrier_thenReturnDetailShippingList() {
        // given
        given(detailShippingRepository.findByTransporter("Carrier")).willReturn(java.util.List.of(detailShipping));
        given(detailShippingMapper.toDto(detailShipping)).willReturn(DetailShippingDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .trackingNumber("123456")
                .transporter("Carrier")
                .build());

        // when
        var result = detailShippingService.getDetailShippingByCarrier("Carrier");

        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(1L, result.get(0).orderId());
        assertEquals(1L, result.get(0).productId());
        assertEquals("123456", result.get(0).trackingNumber());
        assertEquals("Carrier", result.get(0).transporter());
    }

    @Test
    void givenCreateShippingDto_whenCreateDetailShipping_thenReturnDetailShipping() {
        // given
        var createShippingDto = CreateShippingDto.builder()
                .orderId(1L)
                .productId(1L)
                .address("Address")
                .transporter("Carrier")
                .trackingNumber("123456")
                .order(detailShipping.getOrder())
                .product(detailShipping.getProduct())
                .build();

        given(productRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping.getProduct()));
        given(orderRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping.getOrder()));
        given(detailShippingMapper.toEntity(createShippingDto)).willReturn(detailShipping);
        given(detailShippingRepository.save(detailShipping)).willReturn(detailShipping);
        given(detailShippingMapper.toDto(detailShipping)).willReturn(DetailShippingDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .trackingNumber("123456")
                .transporter("Carrier")
                .build());

        // when
        var result = detailShippingService.createDetailShipping(createShippingDto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1L, result.orderId());
        assertEquals(1L, result.productId());
        assertEquals("123456", result.trackingNumber());
        assertEquals("Carrier", result.transporter());
    }

    @Test
    void givenDetailShippingIdAndUpdateShippingDto_whenUpdateDetailShipping_thenReturnUpdatedDetailShipping() {
        // given
        var updateShippingDto = UpdateShippingDto.builder()
                .trackingNumber("654321")
                .transporter("New Carrier")
                .orderId(detailShipping.getOrder().getId())
                .productId(detailShipping.getProduct().getId())
                .build();

        var updatedDetailShipping = DetailShipping.builder()
                .id(1L)
                .product(detailShipping.getProduct())
                .order(detailShipping.getOrder())
                .trackingNumber("654321")
                .transporter("New Carrier")
                .build();

        given(detailShippingRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping));
        given(productRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping.getProduct()));
        given(orderRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping.getOrder()));
        given(detailShippingRepository.save(detailShipping)).willReturn(updatedDetailShipping);
        given(detailShippingMapper.toDto(updatedDetailShipping)).willReturn(DetailShippingDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .trackingNumber("654321")
                .transporter("New Carrier")
                .build());

        // when
        var result = detailShippingService.updateDetailShipping(1L, updateShippingDto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1L, result.orderId());
        assertEquals(1L, result.productId());
        assertEquals("654321", result.trackingNumber());
        assertEquals("New Carrier", result.transporter());
    }

    @Test
    void givenDetailShippingId_whenDeleteDetailShipping_thenDetailShippingShouldBeDeleted() {
        // when
        given(detailShippingRepository.findById(1L)).willReturn(java.util.Optional.of(detailShipping));

        willDoNothing().given(detailShippingRepository).delete(detailShipping);
        detailShippingService.deleteDetailShipping(1L);

        // then
        verify(detailShippingRepository, times(1)).delete(detailShipping);

    }

    @Test
    void givenDetailShippingId_whenDeleteDetailShipping_thenReturnException() {
        // when
        given(detailShippingRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(DetailShippingNotFoundException.class,
                () -> detailShippingService.deleteDetailShipping(any()), "Detail Shipping not found");

    }
}