package com.example.inlamningsuppgiftfmp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RoomDto {
    private Long id;

    @NotBlank(message = "Rumstyp måste anges")
    private String type;

    @Min(value = 0, message = "Antal extrasängar kan inte vara negativt")
    private int maxExtraBed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getMaxExtraBed() { return maxExtraBed; }
    public void setMaxExtraBed(int maxExtraBed) { this.maxExtraBed = maxExtraBed; }

}
