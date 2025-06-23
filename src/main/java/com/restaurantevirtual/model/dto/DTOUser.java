package com.restaurantevirtual.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DTOUser {

    private String name;
    private String email;
    private String password;
    private String dateOfBirth;

}
