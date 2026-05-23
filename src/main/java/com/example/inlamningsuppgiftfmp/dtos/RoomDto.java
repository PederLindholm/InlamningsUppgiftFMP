package com.example.inlamningsuppgiftfmp.dtos;

import com.example.inlamningsuppgiftfmp.models.MaxExtraBed;
import com.example.inlamningsuppgiftfmp.models.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Long id;

    @NotNull(message = "Room type must be filled")
    private RoomType type;

    @NotNull(message = "Number of max extra bed must be filled")
    private MaxExtraBed maxExtraBed;


}
