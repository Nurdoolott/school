package com.example.Spring.project2.mapper;

import com.example.Spring.project2.dto.student.StudentResponse;
import com.example.Spring.project2.entities.Student;

import java.util.List;

public interface StudentMapper {
    StudentResponse toDto(Student student);
    List<StudentResponse> toDtoS(List<Student> all);
}
