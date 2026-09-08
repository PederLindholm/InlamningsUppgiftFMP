package com.example.inlamningsuppgiftfmp.controllers;

import com.example.inlamningsuppgiftfmp.dtos.CustomerDto;
import com.example.inlamningsuppgiftfmp.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/all")
    public String getAll(Model model) {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                "http://customerservice:8081/customers/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );

        model.addAttribute("allCustomers", response.getBody());
        model.addAttribute("name", "Name");
        model.addAttribute("email", "Email");
        model.addAttribute("tel", "Tel");
        model.addAttribute("customerTitle", "All Customers");

        return "customer";
    }


    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            restTemplate.exchange(
                    "http://customerservice:8081/customers/" + id,
                    HttpMethod.DELETE,
                    null,
                    Void.class
            );
            redirectAttributes.addFlashAttribute("success", "Customer deleted successfully");
        } catch (HttpClientErrorException.Conflict e) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete customer with existing bookings");
        } catch (RestClientException e) {
            redirectAttributes.addFlashAttribute("error", "Customer service is currently unavailable. Please try again later.");
        }

       return "redirect:/customer/all";
    }

//    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes){
//        boolean deleted = customerService.deleteCustomer(id);
//
//        if (!deleted){
//            redirectAttributes.addFlashAttribute("error", "Cannot delete customer with existing bookings");
//        } else {
//            redirectAttributes.addFlashAttribute("success", "Customer deleted successfully");
//        }
//        return "redirect:/customer/all";
//    }


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
