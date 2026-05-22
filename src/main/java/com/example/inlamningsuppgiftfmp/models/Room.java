package com.example.inlamningsuppgiftfmp.models;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Enumerated
    private MaxExtraBed maxExtraBed;

    public Room(RoomType type, MaxExtraBed maxExtraBed) {
        this.type = type;
        this.maxExtraBed = maxExtraBed;
    }

}
