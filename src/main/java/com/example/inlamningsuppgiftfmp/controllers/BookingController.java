package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.services.BookingService;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import com.example.inlamningsuppgiftfmp.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(path = "/booking")
public class BookingController {

    private final CustomerService customerService;
    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(CustomerService customerService, BookingService bookingService, RoomService roomService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.roomService = roomService;
    }





}
