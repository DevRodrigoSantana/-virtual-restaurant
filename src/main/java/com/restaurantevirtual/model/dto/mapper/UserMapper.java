package com.restaurantevirtual.model.dto.mapper;


import com.restaurantevirtual.model.dto.DTOUser;
import com.restaurantevirtual.model.dto.DTOResponseUser;
import com.restaurantevirtual.model.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toUser(DTOUser dto){
        return  new ModelMapper().map(dto,User.class);
    }
    public static DTOResponseUser toResponseDto(User user){
        String role = user.getRole().name().substring("ROLE_".length());

        PropertyMap<User, DTOResponseUser> props = new PropertyMap<User, DTOResponseUser>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return  mapper.map(user, DTOResponseUser.class);
    }
    public static List<DTOResponseUser> toListUsers(List<User> users){
        return  users.stream().map(user -> toResponseDto(user)).collect(Collectors.toList());
    }

}
