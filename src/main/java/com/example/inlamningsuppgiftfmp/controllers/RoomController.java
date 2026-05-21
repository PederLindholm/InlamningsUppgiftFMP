package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.models.Room;
import com.example.inlamningsuppgiftfmp.repos.RoomRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class RoomController {

    private final RoomRepo roomRepo;

    public RoomController(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @RequestMapping("rooms")
    public List<Room> getAllRooms(){
        return roomRepo.findAll();
    }

    @RequestMapping("rooms/delete/{id}")
    public String deleteRoom(@PathVariable Long id){
        roomRepo.deleteById(id);
        return "Room "+id+" deleted!";
    }

    @RequestMapping("rooms/add")
    public String addRoom(@RequestParam String type, @RequestParam int maxExtraBed){
        roomRepo.save(new Room(type,maxExtraBed));
        return "Room added";
    }
}
