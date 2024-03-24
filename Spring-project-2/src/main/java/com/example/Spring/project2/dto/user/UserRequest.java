package com.example.Spring.project2.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private LocalDate dateOfBirth;
}
