package com.example.inlamningsuppgiftfmp;

import com.example.inlamningsuppgiftfmp.models.Booking;
import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.models.Room;
import com.example.inlamningsuppgiftfmp.repos.BookingRepo;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import com.example.inlamningsuppgiftfmp.repos.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class InlamningsUppgiftFmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(InlamningsUppgiftFmpApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepo customerRepo, RoomRepo roomRepo, BookingRepo bookingRepo){
        return (args) -> {
            Customer c1 = new Customer("Peder Lindholm","peder@gmail.com","0123456789");
            Customer c2 = new Customer("Farah Sleiman","farah@gmail.com","0987654321");
            Customer c3 = new Customer("Mai Do","mai@gmail.com","0456123789");

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);

            Room r1 = new Room("single",0);
            Room r2 = new Room("double",1);
            Room r3 = new Room("double",2);
            Room r4 = new Room("single",0);
            Room r5 = new Room("double",2);

            roomRepo.save(r1);
            roomRepo.save(r2);
            roomRepo.save(r3);
            roomRepo.save(r4);
            roomRepo.save(r5);

            Booking b1 = new Booking(c1,r5, LocalDate.of(2026,05,30),LocalDate.of(2026,06,15));
            Booking b2 = new Booking(c2,r4, LocalDate.of(2026,06,01),LocalDate.of(2026,06,10));
            Booking b3 = new Booking(c3,r2, LocalDate.of(2026,10,18),LocalDate.of(2026,12,5));

            bookingRepo.save(b1);
            bookingRepo.save(b2);
            bookingRepo.save(b3);
        };
    }

}
