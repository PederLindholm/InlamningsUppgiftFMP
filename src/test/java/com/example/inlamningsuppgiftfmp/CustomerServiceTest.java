package com.example.inlamningsuppgiftfmp;
import com.example.inlamningsuppgiftfmp.dtos.CustomerDto;
import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.repos.BookingRepo;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer("Anna Lindström", "anna@example.com", "070-123 45 67");
        customer.setId(1L);

        customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Anna Lindström");
        customerDto.setEmail("anna@example.com");
        customerDto.setTel("070-123 45 67");
    }

    @Test
    void getAllCustomers_returnsList(){
        when(customerRepo.findAll()).thenReturn(List.of(customer));
        List<CustomerDto> result = customerService.getAllCustomers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Anna Lindström");
    }


    @Test
    void getCustomerByID_returnsDto_whenFound() {
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));

        Optional<CustomerDto> result = customerService.getCustomerByID(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("anna@example.com");
    }

    @Test
    void saveCustomer_savesAndReturnsDto() {
        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        CustomerDto result = customerService.saveCustomer(customerDto);

        assertThat(result.getName()).isEqualTo("Anna Lindström");
        verify(customerRepo, times(1)).save(any(Customer.class));
    }

    @Test
    void updateCustomer_updatesFields_whenFound() {
        CustomerDto updatedDto = new CustomerDto();
        updatedDto.setName("Anna Svensson");
        updatedDto.setEmail("ny@example.com");
        updatedDto.setTel("073-000 00 00");

        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepo.save(any(Customer.class))).thenReturn(customer);

        Optional<CustomerDto> result = customerService.updateCustomer(1L, updatedDto);

        assertThat(result).isPresent();
        verify(customerRepo, times(1)).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_deletesAndReturnsTrue_whenNoBookings() {
        when(bookingRepo.existsByCustomerId(1L)).thenReturn(false);

        boolean result = customerService.deleteCustomer(1L);

        assertThat(result).isTrue();
        verify(customerRepo, times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomer_returnsFalse_whenHasBookings() {
        when(bookingRepo.existsByCustomerId(1L)).thenReturn(true);

        boolean result = customerService.deleteCustomer(1L);

        assertThat(result).isFalse();
        verify(customerRepo, never()).deleteById(any());
    }










}
