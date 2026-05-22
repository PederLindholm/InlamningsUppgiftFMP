package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.models.Room;
import com.example.inlamningsuppgiftfmp.repos.RoomRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private final RoomRepo roomRepo;

    public RoomController(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @RequestMapping("delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomRepo.deleteById(id);
        return "Room " + id + " deleted!";
    }

    @PostMapping("add")
    public String addRoom(@RequestParam String type, @RequestParam int maxExtraBed) {
        roomRepo.save(new Room(type, maxExtraBed));
        return "Room added";
    }
}
