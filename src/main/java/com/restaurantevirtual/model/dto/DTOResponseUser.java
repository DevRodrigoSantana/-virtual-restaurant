package com.restaurantevirtual.model.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DTOResponseUser {
    private UUID id;
    private String name;
    private String role;
}
