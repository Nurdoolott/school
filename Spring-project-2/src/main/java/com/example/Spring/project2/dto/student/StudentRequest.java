package com.example.Spring.project2.dto.student;

import com.example.Spring.project2.dto.user.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest extends UserRequest {
    private int grade;
}
