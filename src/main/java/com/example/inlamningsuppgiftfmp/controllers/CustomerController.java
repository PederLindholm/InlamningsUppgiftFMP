package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.models.Customer;
import com.example.inlamningsuppgiftfmp.repos.CustomerRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @RequestMapping("customers")
    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    @RequestMapping("customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        customerRepo.deleteById(id);
        return "Customer "+id+" deleted!";
    }

    @RequestMapping("customers/add")
    public String addCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String tel){
        customerRepo.save(new Customer(name,email,tel));
        return "Customer " + name + " added";
    }
}
