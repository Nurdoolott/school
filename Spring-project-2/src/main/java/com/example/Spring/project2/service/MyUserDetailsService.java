package com.example.Spring.project2.service;

import com.example.Spring.project2.dto.user.UserRequest;
import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.entities.User;

import java.util.List;

public interface MyUserDetailsService {
    List<UserResponse> getAll();
    UserResponse findByEmail(String userEmail);
    User getUserNameFromToken(String token);
    void updateByEmail(String userEmail, UserRequest userRequest);
    void deleteByEmail(String userEmail);
    void register(UserRequest userRequest);
}
