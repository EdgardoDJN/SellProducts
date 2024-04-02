package com.example.SellProducts.api;

import com.example.SellProducts.dto.order.OrderDto;
import com.example.SellProducts.dto.order.OrderDtoRetrieve;
import com.example.SellProducts.dto.order.OrderToSaveDto;
import com.example.SellProducts.entities.OrderStatus;
import com.example.SellProducts.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@Validated
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }
    //getOrdersByDateBetween
    @GetMapping("/date")
    public ResponseEntity<List<OrderDto>> getOrdersByDateBetween(@RequestParam("dateStart") LocalDateTime dateStart, @RequestParam("dateEnd") LocalDateTime dateEnd) {
        return ResponseEntity.ok().body(orderService.getOrdersByDateBetween(dateStart, dateEnd));
    }
    //getOrdersByCustomerIdAndStatus
    @GetMapping("/customer")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerIdAndStatus(@RequestParam("customerId") Long customerId, @RequestParam("status") OrderStatus status) {
        return ResponseEntity.ok().body(orderService.getOrdersByCustomerIdAndStatus(customerId, status));
    }
    //getOrdersByRetrieveOrdersWithItemsByCustomer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDtoRetrieve>> getOrdersByRetrieveOrdersWithItemsByCustomer(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(orderService.getOrdersByRetrieveOrdersWithItemsByCustomer(customerId));
    }
    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderToSaveDto orderDto) {
        orderService.save(orderDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable("id") Long id, @RequestBody OrderToSaveDto orderDto) {
        orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
