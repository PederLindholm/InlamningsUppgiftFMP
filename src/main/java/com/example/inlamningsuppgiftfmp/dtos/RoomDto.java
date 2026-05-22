package com.example.inlamningsuppgiftfmp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Long id;

    @NotBlank(message = "Room type must be filled")
    private String type;

    @Min(value = 0, message = "Number of extra beds cannot be negative")
    private int maxExtraBed;


}
