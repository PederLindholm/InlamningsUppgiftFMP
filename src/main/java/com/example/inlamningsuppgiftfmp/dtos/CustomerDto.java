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
@Builder
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Namn får inte vara tomt")
    private String name;

    @NotBlank(message = "E-post får inte vara tom")
    @Email(message = "Ogiltig e-postadress")
    private String email;

    @NotBlank(message = "Telefonnummer får inte vara tomt")
    private String tel;

}
