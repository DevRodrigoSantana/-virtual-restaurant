package com.restaurantevirtual.web.controller;


import com.restaurantevirtual.model.dto.DTOUpdatePassword;
import com.restaurantevirtual.model.dto.DTOUser;
import com.restaurantevirtual.model.dto.DTOResponseUser;
import com.restaurantevirtual.model.dto.mapper.UserMapper;
import com.restaurantevirtual.model.entity.User;
import com.restaurantevirtual.service.ServiceUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/user")
public class userController {


    private final ServiceUser service;


    public userController(ServiceUser service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DTOResponseUser> createUser(@RequestBody DTOUser dto){

        User user = service.create(UserMapper.toUser(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponseDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseUser> getUser(@PathVariable UUID id){

        User user = service.findUserId(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toResponseDto(user));
    }
    @GetMapping
    public ResponseEntity<List<DTOResponseUser>> getListUsers(){

        List<User> users = service.searchAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListUsers(users));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){

        service.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id,@RequestBody DTOUpdatePassword dto){

       service.UpdatePassword(dto.getPassedPassword(),dto.getCurrentPassword(),dto.getConfirmPassword(),id);


      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
