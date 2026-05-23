package com.example.inlamningsuppgiftfmp.dtos;

import com.example.inlamningsuppgiftfmp.models.RoomType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Long id;

    @NotNull(message = "Customer must be selected")
    private Long customerId;

    private String customerName;

    @NotNull(message = "Room must be selected")
    private Long roomId;

    private RoomType roomType;

    @NotNull(message = "Check-in date must be selected")
    private LocalDate startDate;

    @NotNull(message = "Check-out date must be selected")
    private LocalDate endDate;

    private Long numberOfNights;
}