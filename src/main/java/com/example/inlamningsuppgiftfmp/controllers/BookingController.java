package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.models.Booking;
import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.models.Room;
import com.example.inlamningsuppgiftfmp.repos.BookingRepo;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import com.example.inlamningsuppgiftfmp.repos.RoomRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

    private final CustomerRepo customerRepo;
    private final BookingRepo bookingRepo;
    private final RoomRepo roomRepo;


    public BookingController(CustomerRepo customerRepo, BookingRepo bookingRepo, RoomRepo roomRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
    }

    @RequestMapping("bookings")
    public List<Booking> getAllBookings(){
        return bookingRepo.findAll();
    }

    @RequestMapping("bookings/delete/{id}")
    public String deleteBooking(@PathVariable Long id){
        bookingRepo.deleteById(id);
        return "Booking "+id+" deleted!";
    }

    @RequestMapping("bookings/add")
    public String addBooking(@RequestParam Long customerId, @RequestParam Long roomId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        Customer customer = customerRepo.findById(customerId).get();
        Room room = roomRepo.findById(roomId).get();
        if (customer != null && room != null){
            bookingRepo.save(new Booking(customer,room,startDate,endDate));
            return "Booking added";
        }
        return "Invalid ID";
    }
}
