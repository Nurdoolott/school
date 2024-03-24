package com.example.Spring.project2.mapper.impl;

import com.example.Spring.project2.dto.student.StudentResponse;
import com.example.Spring.project2.entities.Student;
import com.example.Spring.project2.mapper.StudentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentResponse toDto(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setGrade(student.getGrade());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setRole(String.valueOf(student.getRole()));
        studentResponse.setPassword(student.getPassword());
        studentResponse.setDateOfBirth(student.getDateOfBirth());
        studentResponse.setAge(student.getAge());
        return studentResponse;
    }

    @Override
    public List<StudentResponse> toDtoS(List<Student> all) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : all) {
            studentResponses.add(toDto(student));
        }
        return studentResponses;
    }
}
