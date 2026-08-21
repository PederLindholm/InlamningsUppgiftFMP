package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.dtos.CustomerDto;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/save")
    public String saveCustomer(@Valid CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String firstError = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            model.addAttribute("errorMsg", firstError);
            return "editCustomerForm";
        }
        customerService.saveCustomer(customerDto);
        return "redirect:/customer/all";
    }


    @RequestMapping("/new")
    public String createAddCustomerForm() {
        return "addCustomerForm";
    }


    @PostMapping("/update")
    public String updateCustomer(@Valid CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String firstError = bindingResult.getFieldErrors().get(0).getDefaultMessage();
            model.addAttribute("errorMsg", firstError);
            return "addCustomerForm";
        }
        customerService.saveCustomer(customerDto);
        return "redirect:/customer/all";
    }

}
