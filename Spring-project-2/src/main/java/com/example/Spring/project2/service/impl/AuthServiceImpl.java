package com.example.Spring.project2.service.impl;

import com.example.Spring.project2.config.JwtService;
import com.example.Spring.project2.dto.login.AuthLoginRequest;
import com.example.Spring.project2.dto.login.AuthLoginResponse;
import com.example.Spring.project2.entities.User;
import com.example.Spring.project2.enums.Role;
import com.example.Spring.project2.exception.BadCredentialsException;
import com.example.Spring.project2.repositories.UserRepository;
import com.example.Spring.project2.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public AuthLoginResponse login(AuthLoginRequest authLoginRequest) {
        Optional<User> user = userRepository.findByEmail(authLoginRequest.getEmail());
        if(user.isEmpty()) {
            throw new BadCredentialsException("There is no person under this email address; " + authLoginRequest.getEmail());
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getEmail(), authLoginRequest.getPassword()));
        } catch (Exception e) {
            throw new BadCredentialsException("User not found! ");
        }
        return convertToResponse(user);
    }

    private AuthLoginResponse convertToResponse(Optional<User> user) {
        AuthLoginResponse authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setEmail(user.get().getEmail());
        authLoginResponse.setId(user.get().getId());
        if(user.get().getRole().equals(Role.TEACHER)) {
            authLoginResponse.setName(user.get().getTeacher().getName());
        } else if (user.get().getRole().equals(Role.STUDENT)) {
            authLoginResponse.setName(user.get().getStudent().getName());
        }
        Map<String, Object> extraClaims =new HashMap<>();

        String token = jwtService.generateToken(extraClaims, user.get());
        authLoginResponse.setToken(token);
        return authLoginResponse;
    }
}
