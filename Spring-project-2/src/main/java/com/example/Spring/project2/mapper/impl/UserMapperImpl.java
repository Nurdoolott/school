package com.example.Spring.project2.mapper.impl;

import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.entities.User;
import com.example.Spring.project2.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setLastName(user.getLastName());
        userResponse.setRole(String.valueOf(user.getRole()));
        userResponse.setPassword(user.getPassword());
        userResponse.setEmail(user.getEmail());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setAge(user.getAge());
        return userResponse;
    }

    @Override
    public List<UserResponse> toDtoS(List<User> all) {
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : all) {
            userResponses.add(toDto(user));
        }
        return userResponses;
    }
}
