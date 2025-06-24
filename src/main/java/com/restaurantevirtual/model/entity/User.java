package com.restaurantevirtual.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "Nome",nullable = false)
    private String name;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_ADMIN;
    @Column(name = "data_Aniversario")
    private String dateOfBirth;

    public enum Role{
        ROLE_ADMIN,ROLE_CLIENTE
    }


}
