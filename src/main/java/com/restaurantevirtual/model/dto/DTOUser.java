package com.restaurantevirtual.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DTOUser {
    @NotBlank
    @Pattern(message = "Formato de nome inválido", regexp = "^[\\p{L} .'-]+$")
    private String name;

    @NotBlank
    @Email(message = "Formato de email está inválido",regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;

    @NotBlank
    @Size(min = 6,max = 20)
    @Pattern(message = "Formato de senha não contem caracteres validos ",  regexp = "^[a-zA-Z0-9\\s\\p{Punct}]+$")
    private String password;

    @NotBlank
    @Pattern(message = "data de nascimento de formato Incorreto",regexp = "^\\d{2}/\\d{2}/\\d{4}$" )
    private String dateOfBirth;

}
