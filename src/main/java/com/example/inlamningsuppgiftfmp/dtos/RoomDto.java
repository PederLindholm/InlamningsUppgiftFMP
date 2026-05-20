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
@Builder
public class RoomDto {
    private Long id;

    @NotBlank(message = "Rumstyp måste anges")
    private String type;

    @Min(value = 0, message = "Antal extrasängar kan inte vara negativt")
    private int maxExtraBed;


}
