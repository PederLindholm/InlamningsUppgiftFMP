package com.example.inlamningsuppgiftfmp.dtos;

import com.example.inlamningsuppgiftfmp.models.MaxExtraBed;
import com.example.inlamningsuppgiftfmp.models.RoomType;
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
    private RoomType type;

    @Min(value = 0, message = "Number of extra beds cannot be negative")
    private MaxExtraBed maxExtraBed;


}
