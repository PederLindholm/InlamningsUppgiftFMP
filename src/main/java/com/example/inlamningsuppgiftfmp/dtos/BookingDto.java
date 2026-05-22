package com.example.inlamningsuppgiftfmp.dtos;

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

    @NotNull
    private Long customerId;

    @NotNull
    private Long roomId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}