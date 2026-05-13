package com.example.inlamningsuppgiftfmp.repos;

import com.example.inlamningsuppgiftfmp.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepo extends JpaRepository<Costumer, Long> {
}
