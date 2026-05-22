package com.example.inlamningsuppgiftfmp.services;

import com.example.inlamningsuppgiftfmp.dtos.BookingDto;
import com.example.inlamningsuppgiftfmp.models.Booking;
import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.models.Room;
import com.example.inlamningsuppgiftfmp.repos.BookingRepo;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import com.example.inlamningsuppgiftfmp.repos.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepo bookingRepo;
    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;

    public BookingService(BookingRepo bookingRepo,
                          CustomerRepo customerRepo,
                          RoomRepo roomRepo) {
        this.bookingRepo = bookingRepo;
        this.customerRepo = customerRepo;
        this.roomRepo = roomRepo;
    }

    public List<BookingDto> getAllBookings() {
        return bookingRepo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<BookingDto> getBookingById(Long id) {
        return bookingRepo.findById(id)
                .map(this::toDto);
    }

    public BookingDto createBooking(BookingDto dto) {

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Kund hittades inte"));

        Room room = roomRepo.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Rum hittades inte"));

        boolean overlaps = bookingRepo.existsOverlappingBooking(
                room.getId(),
                dto.getStartDate(),
                dto.getEndDate()
        );

        if (overlaps) {
            throw new RuntimeException("Rummet är redan bokat för detta datum");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());

        return toDto(bookingRepo.save(booking));
    }

    public Optional<BookingDto> updateBooking(Long id, BookingDto dto) {

        return bookingRepo.findById(id).map(existing -> {

            Customer customer = customerRepo.findById(dto.getCustomerId())
                    .orElseThrow();

            Room room = roomRepo.findById(dto.getRoomId())
                    .orElseThrow();

            boolean overlaps = bookingRepo.existsOverlappingBooking(
                    room.getId(),
                    dto.getStartDate(),
                    dto.getEndDate()
            );

            if (overlaps &&
                    !(existing.getRoom().getId().equals(room.getId())
                            && existing.getStartDate().equals(dto.getStartDate())
                            && existing.getEndDate().equals(dto.getEndDate()))) {

                throw new RuntimeException("Rummet är redan bokat");
            }

            existing.setCustomer(customer);
            existing.setRoom(room);
            existing.setStartDate(dto.getStartDate());
            existing.setEndDate(dto.getEndDate());

            return toDto(bookingRepo.save(existing));
        });
    }

    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    private BookingDto toDto(Booking booking) {

        BookingDto dto = new BookingDto();

        dto.setId(booking.getId());
        dto.setCustomerId(booking.getCustomer().getId());
        dto.setRoomId(booking.getRoom().getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());

        return dto;
    }
}