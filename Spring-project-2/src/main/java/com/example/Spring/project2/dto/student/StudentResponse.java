package com.example.Spring.project2.dto.student;

import com.example.Spring.project2.dto.user.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse extends UserResponse {
    private int grade;
}
