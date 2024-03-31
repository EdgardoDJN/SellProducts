package com.example.SellProducts.service;

import com.example.SellProducts.dto.product.ProductDto;
import com.example.SellProducts.dto.product.ProductToSaveDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByName(String name);
    List<ProductDto> getProductsByStockGreaterThan(int quantity);
    List<ProductDto> getProductsByPriceLessThanAndStockLessThan(double maxPrice, int maxStock);
    ProductDto createProduct(ProductToSaveDto productDto);
    ProductDto updateProduct(Long id, ProductToSaveDto productDto);
    void deleteProduct(Long id);
}
