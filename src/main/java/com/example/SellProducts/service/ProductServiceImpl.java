package com.example.SellProducts.service;

import com.example.SellProducts.dto.product.ProductDto;
import com.example.SellProducts.dto.product.ProductMapper;
import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.entities.Product;
import com.example.SellProducts.exception.ProductNotFoundException;
import com.example.SellProducts.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        var products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByName(String name) {
        var products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByStockGreaterThan(int quantity) {
        var products = productRepository.findByStockGreaterThan(quantity);
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByPriceLessThanAndStockLessThan(double maxPrice, int maxStock) {
        var products = productRepository.findByPriceLessThanAndStockLessThan(maxPrice, maxStock);
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto createProduct(ProductToSaveDto productDto) {
        //Ojo aca que despues no encuentra lo referente a orderItems, tener en cuenta para futuras modificaciones
        Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
        //return productDto;
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductToSaveDto productDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDto.name());
                    product.setPrice(productDto.price());
                    product.setStock(productDto.stock());
                    productRepository.save(product);
                    return productMapper.toDto(product);
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteProduct(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}
