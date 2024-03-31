package com.example.SellProducts.service;

import com.example.SellProducts.dto.customer.CustomerDto;
import com.example.SellProducts.dto.customer.CustomerMapper;
import com.example.SellProducts.dto.customer.CustomerToSaveDto;
import com.example.SellProducts.entities.Customer;
import com.example.SellProducts.exception.CustomerNotFoundException;
import com.example.SellProducts.exception.ProductNotFoundException;
import com.example.SellProducts.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;
    Customer customer;
    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("eda@gmai.com");
        customer.setAddress("1234 Main St");
    }

    @Test
    void givenEmail_whenGetCustomersByEmail_thenReturnCustomers() {
        // Given
        given(customerRepository.findByEmail("eda@gmai.com")).willReturn(List.of(customer));
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // When
        var customers = customerService.getCustomersByEmail("eda@gmai.com");
        // Then
        assertThat(customers).isNotEmpty();
        assertThat(customers.get(0).id()).isEqualTo(1L);
        assertThat(customers.get(0).name()).isEqualTo("John Doe");
        assertThat(customers.get(0).email()).isEqualTo("eda@gmai.com");
        assertThat(customers.get(0).address()).isEqualTo("1234 Main St");
    }

    @Test
    void givenEmail_whenGetCustomersByEmail_thenReturnEmptyList() {
        // Given
        given(customerRepository.findByEmail("eda@gmai.com")).willReturn(List.of());
        // When
        var customers = customerService.getCustomersByEmail("eda@gmai.com");
        // Then
        assertThat(customers).isEmpty();
    }

    @Test
    void givenAddress_whenGetCustomersByAddress_thenReturnCustomers() {
        // Given
        given(customerRepository.findByAddress("1234 Main St")).willReturn(List.of(customer));
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // When
        var customers = customerService.getCustomersByAddress("1234 Main St");
        // Then
        assertThat(customers).isNotEmpty();
        assertThat(customers.get(0).id()).isEqualTo(1L);
        assertThat(customers.get(0).name()).isEqualTo("John Doe");
        assertThat(customers.get(0).email()).isEqualTo("eda@gmai.com");
        assertThat(customers.get(0).address()).isEqualTo("1234 Main St");
    }

    @Test
    void givenAddress_whenGetCustomersByAddress_thenReturnEmptyList() {
        // Given
        given(customerRepository.findByAddress("1234 Main St")).willReturn(List.of());
        // When
        var customers = customerService.getCustomersByAddress("1234 Main St");
        // Then
        assertThat(customers).isEmpty();
    }

    @Test
    void givenName_whenGetCustomersByNameStartingWith_thenReturnCustomers() {
        // Given
        given(customerRepository.findByNameStartingWith("John")).willReturn(List.of(customer));
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // When
        var customers = customerService.getCustomersByNameStartingWith("John");
        // Then
        assertThat(customers).isNotEmpty();
        assertThat(customers.get(0).id()).isEqualTo(1L);
        assertThat(customers.get(0).name()).isEqualTo("John Doe");
        assertThat(customers.get(0).email()).isEqualTo("eda@gmai.com");
        assertThat(customers.get(0).address()).isEqualTo("1234 Main St");
    }

    @Test
    void givenName_whenGetCustomersByNameStartingWith_thenReturnEmptyList() {
        // Given
        given(customerRepository.findByNameStartingWith("John")).willReturn(List.of());
        // When
        var customers = customerService.getCustomersByNameStartingWith("John");
        // Then
        assertThat(customers).isEmpty();
    }

    @Test
    void givenProductId_whenFindById_thenReturnCustomer() {
        // Given
        given(customerRepository.findById(1L)).willReturn(java.util.Optional.of(customer));
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // When
        var customer = customerService.findById(1L);
        // Then
        assertThat(customer.id()).isEqualTo(1L);
        assertThat(customer.name()).isEqualTo("John Doe");
        assertThat(customer.email()).isEqualTo("eda@gmai.com");
        assertThat(customer.address()).isEqualTo("1234 Main St");
    }

    @Test
    void givenProductId_whenGetProductById_thenThrowProductNotFoundException() {
        // Given
        Long id = 1L;
        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(id), "Customer not found");
    }

    @Test
    void givenCustomers_whenFindAll_thenReturnCustomers() {
        // Given
        given(customerRepository.findAll()).willReturn(List.of(customer));
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // When
        var customers = customerService.findAll();
        // Then
        assertThat(customers).isNotEmpty();
        assertThat(customers.get(0).id()).isEqualTo(1L);
        assertThat(customers.get(0).name()).isEqualTo("John Doe");
        assertThat(customers.get(0).email()).isEqualTo("eda@gmai.com");
        assertThat(customers.get(0).address()).isEqualTo("1234 Main St");
    }
    @Test
    void givenCustomer_whenCreateCustomer_thenReturnCustomer() {
        // Given
        var customerToSaveDto = CustomerToSaveDto.builder().name("John Doe").email("eda@gmai.com").address("1234 Main St").build();

        given(customerMapper.toEntity(customerToSaveDto)).willReturn(customer);
        given(customerRepository.save(customer)).willReturn(customer);
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());

        // When
        var found = customerService.save(customerToSaveDto);

        // Then
        assertThat(found).isNotNull();
        assertEquals(found.id(), 1L);
        assertEquals(found.name(), "John Doe");
        assertEquals(found.email(), "eda@gmai.com");
        assertEquals(found.address(), "1234 Main St");
    }
    @Test
    void givenCustomerId_whenDeleteCustomer_thenCustomerShouldBeDeleted() {
        // Given
        Long id = 1L;
        given(customerRepository.findById(id)).willReturn(Optional.of(customer));

        willDoNothing().given(customerRepository).delete(customer);
        customerService.deleteCustomer(id);

        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void givenCustomerId_whenDeleteCustomer_thenThrowCustomerNotFoundException() {
        // Given
        Long id = 1L;
        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(id), "Customer not found");
    }

    @Test
    void givenCustomer_whenUpdate_thenReturnCustomerUpdated() {
        // Given
        given(customerRepository.findById(1L)).willReturn(java.util.Optional.of(customer));
        given(customerRepository.save(customer)).willReturn(customer);
        given(customerMapper.toDto(customer)).willReturn(CustomerDto.builder().id(1L).name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // When
        var customer = customerService.update(1L, CustomerToSaveDto.builder().name("John Doe").email("eda@gmai.com").address("1234 Main St").build());
        // Then
        assertThat(customer.id()).isEqualTo(1L);
        assertThat(customer.name()).isEqualTo("John Doe");
        assertThat(customer.email()).isEqualTo("eda@gmai.com");
        assertThat(customer.address()).isEqualTo("1234 Main St");
        }
}
