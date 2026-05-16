package com.example.inlamningsuppgiftfmp.repos;

import com.example.inlamningsuppgiftfmp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
