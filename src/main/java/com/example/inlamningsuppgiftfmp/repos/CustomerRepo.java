package com.example.inlamningsuppgiftfmp.repos;

import com.example.inlamningsuppgiftfmp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Long id(Long id);
}
