package com.example.inlamningsuppgiftfmp.services;

import com.example.inlamningsuppgiftfmp.dtos.CustomerDto;
import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.repos.BookingRepo;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final BookingRepo bookingRepo;


    public CustomerService(CustomerRepo customerRepo, BookingRepo bookingRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
    }

    public List<CustomerDto> getAllCustomers(){
        return customerRepo.findAll().stream().map(this::toDto).toList();
    }

    public Optional<CustomerDto> getCustomerByID(Long id){
        return customerRepo.findById(id).map(this::toDto);
    }

    public CustomerDto saveCustomer(CustomerDto dto){
        Customer customer = toEntity(dto);
        Customer saved = customerRepo.save(customer);
        return toDto(saved);

    }

    public Optional<CustomerDto> updateCustomer(Long id, CustomerDto dto) {
        return customerRepo.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setTel(dto.getTel());
            return toDto(customerRepo.save(existing));
        });
    }

    public boolean deleteCustomer(Long id) {
        boolean hasBookings = bookingRepo.existsByCustomerId(id);
        if (hasBookings) {
            return false;
        }
        customerRepo.deleteById(id);
        return true;
    }


    public CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setTel(customer.getTel());
        return dto;
    }

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        if (dto.getId() != null) {
            customer.setId(dto.getId());
        }
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setTel(dto.getTel());
        return customer;
    }

}
