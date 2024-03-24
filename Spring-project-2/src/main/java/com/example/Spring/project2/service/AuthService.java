package com.example.Spring.project2.service;

import com.example.Spring.project2.dto.login.AuthLoginRequest;
import com.example.Spring.project2.dto.login.AuthLoginResponse;

public interface AuthService {
    AuthLoginResponse login (AuthLoginRequest authLoginRequest);
}
