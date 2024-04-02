package com.example.SellProducts.api;

import com.example.SellProducts.dto.customer.CustomerDto;
import com.example.SellProducts.dto.customer.CustomerToSaveDto;
import com.example.SellProducts.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@Validated
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<CustomerDto>> getCustomerByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(customerService.getCustomersByEmail(email));
    }

    @GetMapping("/address")
    public ResponseEntity<List<CustomerDto>> getCustomersByAddress(@RequestParam("address") String address) {
        return ResponseEntity.ok().body(customerService.getCustomersByAddress(address));
    }

    @GetMapping("/name")
    public ResponseEntity<List<CustomerDto>> getCustomersByNameStartingWith(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(customerService.getCustomersByNameStartingWith(name));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerToSaveDto customerDto) {
        customerService.save(customerDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerToSaveDto customerDto) {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
