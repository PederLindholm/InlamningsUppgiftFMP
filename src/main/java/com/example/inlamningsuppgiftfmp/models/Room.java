package com.example.inlamningsuppgiftfmp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private int maxExtraBed;

    public Room(String type, int maxExtraBed) {
        this.type = type;
        this.maxExtraBed = maxExtraBed;
    }

}
