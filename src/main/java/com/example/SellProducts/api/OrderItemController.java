package com.example.SellProducts.api;

import com.example.SellProducts.dto.orderItem.*;
import com.example.SellProducts.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{Id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("Id") Long Id) {
        return ResponseEntity.ok().body(orderItemService.getOrderItem(Id));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getOrderItems() {
        return ResponseEntity.ok().body(orderItemService.getOrderItems());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(orderItemService.getOrderItemsByOrderId(orderId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemsByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok().body(orderItemService.getOrderItemsByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody CreateOrderItemDto createOrderItemDto) {
        return ResponseEntity.ok().body(orderItemService.createOrderItem(createOrderItemDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(
            @PathVariable("id") Long id,
            @RequestBody UpdateOrderItemDto updateOrderItemDto) {
        return ResponseEntity.ok().body(orderItemService.updateOrderItem(id, updateOrderItemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}
