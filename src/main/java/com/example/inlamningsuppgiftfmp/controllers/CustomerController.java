package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerRepo customerRepo;
    private final CustomerService customerService;

    public CustomerController(CustomerRepo customerRepo, CustomerService customerService) {
        this.customerRepo = customerRepo;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @RequestMapping("delete/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model) {
        boolean deleted = customerService.deleteCustomer(id);
        if (!deleted) {
            model.addAttribute("error", "Kan inte ta bort kunder med bokningar");
        }
        return "redirect:/customers";
    }

    @PostMapping("add")
    public String addCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String tel) {
        customerRepo.save(new Customer(name, email, tel));
        return "Customer " + name + " added";
    }
}
