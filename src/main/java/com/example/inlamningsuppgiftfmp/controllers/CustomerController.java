package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.dtos.CustomerDto;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/all")
    public String getAll(Model model) {

        List<CustomerDto> customerDtoList = customerService.getAllCustomers();

        model.addAttribute("allCustomers", customerDtoList);
        model.addAttribute("name", "Name");
        model.addAttribute("email", "Email");
        model.addAttribute("tel", "Tel");
        model.addAttribute("customerTitle", "All Customers");

        return "customer";
    }


    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes){
       boolean deleted = customerService.deleteCustomer(id);

       if (!deleted){
           redirectAttributes.addFlashAttribute("error", "Cannot delete customer with existing bookings");
       } else {
           redirectAttributes.addFlashAttribute("success", "Customer deleted successfully");
       }

       return "redirect:/customer/all";
    }

    //edit customer: clicking on "edit" button and the site will go to a form to edit customer's info
    @RequestMapping("/edit/{id}")
    public String createEditCustomerForm(@PathVariable Long id, Model model) {
        Optional<CustomerDto> optionalCustomer = customerService.getCustomerByID(id);

        if (optionalCustomer.isEmpty()) {
            model.addAttribute("error", "Customer not found");
            return "redirect:/customer/all";
        }

        model.addAttribute("customer", optionalCustomer.get());

        return "editCustomerForm";
    }


    //adding new customer, when clicking on "Add new customer", this function send out a form to fill in info
    @RequestMapping("/new")
    public String createAddCustomerForm() {
        return "addCustomerForm";
    }

    //update customer: after clicking submit on the form (either edit form or add form, customer is saved and go back to all customers list
    @PostMapping("/update")
    public String saveEditedCustomer(CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return "redirect:/customer/all";
    }

}
