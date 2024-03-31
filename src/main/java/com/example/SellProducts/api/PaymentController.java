package com.example.SellProducts.api;

import com.example.SellProducts.dto.payment.CreatePaymentDTO;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.dto.payment.UpdatePaymentDTO;
import com.example.SellProducts.entities.methodPayment;
import com.example.SellProducts.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok().body(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(paymentService.getPaymentById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByOrderId(@PathVariable("orderId") Long orderId, @RequestParam methodPayment method) {
        return ResponseEntity.ok().body(paymentService.getPaymentsByOrderId(orderId, method));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDTO>> getPaymentsForDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return ResponseEntity.ok().body(paymentService.getPaymentsForDateRange(start, end));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody CreatePaymentDTO createPaymentDTO) {
        return ResponseEntity.ok().body(paymentService.createPayment(createPaymentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> UpdatePayment(@PathVariable("id") Long id, @RequestBody UpdatePaymentDTO updatePaymentDTO) {
        return ResponseEntity.ok().body(paymentService.UpdatePayment(id, updatePaymentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
