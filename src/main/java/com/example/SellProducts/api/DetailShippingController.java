package com.example.SellProducts.api;

import com.example.SellProducts.dto.detailShipping.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SellProducts.service.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shipping")
public class DetailShippingController {

    private final DetailShippingService detailShippingService;

    public DetailShippingController(DetailShippingService detailShippingService) {
        this.detailShippingService = detailShippingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailShippingDto> getDetailShippingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(detailShippingService.getDetailShippingById(id));
    }

    @GetMapping
    public ResponseEntity<List<DetailShippingDto>> getAllDetailShipping() {
        return ResponseEntity.ok().body(detailShippingService.getAllDetailShipping());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DetailShippingDto>> getDetailShippingByOrderId(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(detailShippingService.getDetailShippingByOrderId(orderId));
    }

    @GetMapping("/carrier/")
    public ResponseEntity<List<DetailShippingDto>> getDetailShippingByCarrier(@RequestParam String name) {
        return ResponseEntity.ok().body(detailShippingService.getDetailShippingByCarrier(name));
    }

    @PostMapping
    public ResponseEntity<DetailShippingDto> createDetailShipping(
            @Valid @RequestBody CreateShippingDto createShippingDto) {
        return ResponseEntity.ok().body(detailShippingService.createDetailShipping(createShippingDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailShippingDto> updateDetailShipping(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateShippingDto updateShippingDto) {
        return ResponseEntity.ok().body(detailShippingService.updateDetailShipping(id, updateShippingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailShipping(@PathVariable("id") Long id) {
        detailShippingService.deleteDetailShipping(id);
        return ResponseEntity.ok().build();
    }
}
