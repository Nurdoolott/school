package com.example.Spring.project2.controllers;

import com.example.Spring.project2.dto.login.AuthLoginRequest;
import com.example.Spring.project2.dto.login.AuthLoginResponse;
import com.example.Spring.project2.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthLoginResponse login(@RequestBody AuthLoginRequest authLoginRequest){
        return authService.login(authLoginRequest);
    }
}
