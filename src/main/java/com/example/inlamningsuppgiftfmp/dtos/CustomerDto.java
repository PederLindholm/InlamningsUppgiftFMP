package com.example.inlamningsuppgiftfmp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Name shall not be blank")
    private String name;

    @NotBlank(message = "Email shall not be blank")
    @Email(message = "Not valid email address")
    private String email;

    @NotBlank(message = "Telephone number shall not be blank")
    private String tel;

}
