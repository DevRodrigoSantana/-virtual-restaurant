package com.restaurantevirtual.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DTOUpdatePassword {
    @NotBlank
    @Size(min = 6,max = 20)
    @Pattern(message = "Formato de senha não contem caracteres validos ",  regexp = "^[a-zA-Z0-9\\s\\p{Punct}]+$")
    private String passedPassword;
    @NotBlank
    @Size(min = 6,max = 20)
    @Pattern(message = "Formato de senha não contem caracteres validos ",  regexp = "^[a-zA-Z0-9\\s\\p{Punct}]+$")
    private String confirmPassword;
    @NotBlank
    @Size(min = 6,max = 20)
    @Pattern(message = "Formato de senha não contem caracteres validos ",  regexp = "^[a-zA-Z0-9\\s\\p{Punct}]+$")
    private String currentPassword;
}
