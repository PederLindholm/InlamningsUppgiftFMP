package com.example.inlamningsuppgiftfmp.repos;

import com.example.inlamningsuppgiftfmp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    boolean existsByCustomerId(Long costumerId);

    @Query("SELECT b.room.id FROM Booking b WHERE b.startDate < :endDate AND b.endDate > :startDate")
    List<Long> findBookedRoomIds(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.room.id = :roomId AND b.startDate < :endDate AND b.endDate > :startDate")
    boolean existsOverlappingBooking(@Param("roomId") Long roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
