package com.example.SellProducts.service;

import com.example.SellProducts.dto.product.ProductDto;
import com.example.SellProducts.dto.product.ProductMapper;
import com.example.SellProducts.dto.product.ProductToSaveDto;
import com.example.SellProducts.entities.Product;
import com.example.SellProducts.exception.ProductNotFoundException;
import com.example.SellProducts.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

//Seguir mismo orden que se lleva en la clase ProductServiceImpl
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setStock(10);
    }

    @Test
    void givenProductId_whenGetProductById_thenReturnProductDto() {
        // Given
        Long id = 1L;
        given(productRepository.findById(id)).willReturn(Optional.of(product));
        given(productMapper.toDto(product)).willReturn(ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build());

        // When
        ProductDto productDto = productService.getProductById(id);

        // Then
        assertThat(productDto).isNotNull();
        assertThat(productDto.id()).isEqualTo(product.getId());
        assertThat(productDto.name()).isEqualTo(product.getName());
        assertThat(productDto.price()).isEqualTo(product.getPrice());
        assertThat(productDto.stock()).isEqualTo(product.getStock());
    }

    @Test
    void givenProductId_whenGetProductById_thenThrowProductNotFoundException() {
        // Given
        Long id = 1L;
        given(productRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(id));
    }

    @Test
    void givenProduct_whenGetAllProducts_thenReturnListOfProductDto() {
        // Given
        given(productRepository.findAll()).willReturn(java.util.List.of(product));
        given(productMapper.toDto(product)).willReturn(ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build());

        // When
        var products = productService.getAllProducts();

        // Then
        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).id()).isEqualTo(product.getId());
        assertThat(products.get(0).name()).isEqualTo(product.getName());
        assertThat(products.get(0).price()).isEqualTo(product.getPrice());
        assertThat(products.get(0).stock()).isEqualTo(product.getStock());
    }

    @Test
    void givenProductName_whenGetProductsByName_thenReturnListOfProductDto() {
        // Given
        String name = "Product 1";
        given(productRepository.findByNameContainingIgnoreCase(name)).willReturn(java.util.List.of(product));
        given(productMapper.toDto(product)).willReturn(ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build());

        // When
        var products = productService.getProductsByName(name);

        // Then
        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).id()).isEqualTo(product.getId());
        assertThat(products.get(0).name()).isEqualTo(product.getName());
        assertThat(products.get(0).price()).isEqualTo(product.getPrice());
        assertThat(products.get(0).stock()).isEqualTo(product.getStock());
    }

    @Test
    void givenQuantity_whenGetProductsByStockGreaterThan_thenReturnListOfProductDto() {
        // Given
        int quantity = 5;
        given(productRepository.findByStockGreaterThan(quantity)).willReturn(java.util.List.of(product));
        given(productMapper.toDto(product)).willReturn(ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build());

        // When
        var products = productService.getProductsByStockGreaterThan(quantity);

        // Then
        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).id()).isEqualTo(product.getId());
        assertThat(products.get(0).name()).isEqualTo(product.getName());
        assertThat(products.get(0).price()).isEqualTo(product.getPrice());
        assertThat(products.get(0).stock()).isEqualTo(product.getStock());
    }

    @Test
    void givenMaxPriceAndMaxStock_whenGetProductsByPriceLessThanAndStockLessThan_thenReturnListOfProductDto() {
        // Given
        double maxPrice = 15.0;
        int maxStock = 15;
        given(productRepository.findByPriceLessThanAndStockLessThan(maxPrice, maxStock)).willReturn(java.util.List.of(product));
        given(productMapper.toDto(product)).willReturn(ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build());

        // When
        var products = productService.getProductsByPriceLessThanAndStockLessThan(maxPrice, maxStock);

        // Then
        assertThat(products).isNotEmpty();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).id()).isEqualTo(product.getId());
        assertThat(products.get(0).name()).isEqualTo(product.getName());
        assertThat(products.get(0).price()).isEqualTo(product.getPrice());
        assertThat(products.get(0).stock()).isEqualTo(product.getStock());
    }

    @Test
    void givenProduct_whenCreateProduct_thenReturnProduct() {
        // Given
        var productToSaveDto = ProductToSaveDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

        given(productMapper.toEntity(productToSaveDto)).willReturn(product);
        given(productRepository.save(product)).willReturn(product);
        given(productMapper.toDto(product)).willReturn(ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build());

        // When
        var found = productService.createProduct(productToSaveDto);

        // Then
        assertThat(found).isNotNull();
        assertEquals(found.name(), product.getName());
        assertEquals(found.price(), product.getPrice());
        assertEquals(found.stock(), product.getStock());
    }

    @Test
    void givenProduct_whenUpdateProduct_thenReturnProduct() {
        // Given
        var productToSaveDto = ProductToSaveDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
        var productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
        given(productRepository.save(any(Product.class))).willReturn(product);
        given(productMapper.toDto(product)).willReturn(productDto);


        // When
        var found = productService.updateProduct(product.getId(), productToSaveDto);

        // Then
        assertThat(found).isNotNull();
        assertEquals(found.id(), product.getId());
        assertEquals(found.name(), product.getName());
        assertEquals(found.price(), product.getPrice());
        assertEquals(found.stock(), product.getStock());
    }

    @Test
    void givenProductId_whenUpdateProduct_thenProductNotFoundException() {
        // Given
        Long id = 1L;
        given(productRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(id, ProductToSaveDto.builder().build()));
    }

    @Test
    void givenProductId_whenDeleteProduct_thenProductShouldBeDeleted() {
        // Given
        Long id = 1L;
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        // When
        productService.deleteProduct(id);

        // Then
        assertThat(productRepository.existsById(id)).isFalse();
    }

    @Test
    void givenProductId_whenDeleteProduct_thenProductNotFoundException() {
        // Given
        Long id = 1L;
        given(productRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(id));
    }


}
