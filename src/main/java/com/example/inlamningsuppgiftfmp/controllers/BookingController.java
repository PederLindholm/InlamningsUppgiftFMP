package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.dtos.BookingDto;
import com.example.inlamningsuppgiftfmp.services.BookingService;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import com.example.inlamningsuppgiftfmp.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingController {

    private final CustomerService customerService;
    private final BookingService bookingService;
    private final RoomService roomService;


    @RequestMapping("/all")
    public String getAllBooking(Model model) {

        List<BookingDto> bookingDtoList = bookingService.getAllBookings();

        model.addAttribute("allBookings", bookingDtoList);
        model.addAttribute("customerName", "Customer Name");
        model.addAttribute("roomId", "Room ID");
        model.addAttribute("roomType", "Room Type");
        model.addAttribute("startDate","Check-in Date");
        model.addAttribute("endDate","Check-out Date");
        model.addAttribute("numberOfNights","Nights");
        model.addAttribute("bookingTitle", "All Bookings");

        return "booking";
    }


    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return "redirect:/booking/all";
    }


    @RequestMapping("/edit/{id}")
    public String createEditBookingForm(@PathVariable Long id, Model model) {
        Optional<BookingDto> optionalBooking = bookingService.getBookingById(id);

        if (optionalBooking.isEmpty()) {
            model.addAttribute("error", "Booking not found");
            return "redirect:/booking/all";
        }

        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("booking", optionalBooking.get());

        return "editBookingForm";
    }


    @PostMapping("/update")
    public String updateEditedBooking(@Valid @ModelAttribute("booking") BookingDto bookingDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String firstError = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            model.addAttribute("errorMsg", firstError);
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "editBookingForm";
        }

        try {
            bookingService.updateBooking(bookingDto);
            redirectAttributes.addFlashAttribute("success", "Booking updated successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/booking/all";
    }


    @RequestMapping("/new")
    public String createAddBookingForm(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("booking", new BookingDto());
        return "addBookingForm";
    }


    @PostMapping("/create")
    public String createNewBooking(@Valid @ModelAttribute("booking") BookingDto bookingDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String firstError = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            model.addAttribute("errorMsg", firstError);
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "addBookingForm";
        }

        try {
            bookingService.createBooking(bookingDto);
            redirectAttributes.addFlashAttribute("success", "Booking created successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/booking/all";
    }

}
