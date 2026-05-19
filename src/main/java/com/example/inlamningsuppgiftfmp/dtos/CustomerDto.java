package com.example.inlamningsuppgiftfmp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerDto {
    private Long id;

    @NotBlank(message = "Namn får inte vara tomt")
    private String name;

    @NotBlank(message = "E-post får inte vara tom")
    @Email(message = "Ogiltig e-postadress")
    private String email;

    @NotBlank(message = "Telefonnummer får inte vara tomt")
    private String tel;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

}
