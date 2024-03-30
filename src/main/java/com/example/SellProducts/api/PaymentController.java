package com.example.SellProducts.api;

import com.example.SellProducts.dto.payment.CreatePaymentDTO;
import com.example.SellProducts.dto.payment.PaymentDTO;
import com.example.SellProducts.dto.payment.UpdatePaymentDTO;
import com.example.SellProducts.entities.methodPayment;
import com.example.SellProducts.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
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
        try{
            return ResponseEntity.ok().body(paymentService.getPaymentById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByOrderId(@PathVariable("orderId") Long orderId, methodPayment method) {
        try{
            return ResponseEntity.ok().body(paymentService.getPaymentsByOrderId(orderId, method));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDTO>> getPaymentsForDateRange(LocalDate start, LocalDate end) {
        return ResponseEntity.ok().body(paymentService.getPaymentsForDateRange(start, end));
    }

    @PostMapping
    public ResponseEntity<CreatePaymentDTO> createPayment(CreatePaymentDTO createPaymentDTO) {
        return ResponseEntity.ok().body(paymentService.createPayment(createPaymentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> UpdatePayment(@PathVariable("id") Long id, UpdatePaymentDTO updatePaymentDTO) {
        return ResponseEntity.ok().body(paymentService.UpdatePayment(id, updatePaymentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id) {
        try{
            paymentService.deletePayment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
