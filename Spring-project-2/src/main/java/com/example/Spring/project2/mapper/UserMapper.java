package com.example.Spring.project2.mapper;

import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.entities.User;

import java.util.List;

public interface UserMapper {
    UserResponse toDto(User user);
    List<UserResponse> toDtoS(List<User> all);
}
