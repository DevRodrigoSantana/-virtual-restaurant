package com.restaurantevirtual.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DTOUpdatePassword {
    private String passedPassword;
    private String confirmPassword;
    private String currentPassword;
}
