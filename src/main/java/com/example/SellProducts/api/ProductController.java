package com.example.SellProducts.api;

import com.example.SellProducts.dto.product.ProductDto;
import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @GetMapping("/name")
    public ResponseEntity<List<ProductDto>> getProductsByName(@RequestParam String name) {
        return ResponseEntity.ok().body(productService.getProductsByName(name));
    }
    @GetMapping("/stock/{quantity}")
    public ResponseEntity<List<ProductDto>> getProductsByStockGreaterThan(@PathVariable("quantity") int quantity) {
        return ResponseEntity.ok().body(productService.getProductsByStockGreaterThan(quantity));
    }

    @GetMapping("/price-stock")
    public ResponseEntity<List<ProductDto>> getProductsByPriceLessThanAndStockLessThan(@RequestParam double maxPrice, @RequestParam int maxStock) {
        return ResponseEntity.ok().body(productService.getProductsByPriceLessThanAndStockLessThan(maxPrice, maxStock));
    }
    //createProduct
    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductToSaveDto productDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductToSaveDto productDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
