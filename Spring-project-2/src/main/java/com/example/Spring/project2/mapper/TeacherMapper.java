package com.example.Spring.project2.mapper;

import com.example.Spring.project2.dto.teacher.TeacherResponse;
import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.entities.Teacher;

import java.util.List;

public interface TeacherMapper {
    TeacherResponse toDto(Teacher teacher);
    List<TeacherResponse> toDtoS(List<Teacher> all);
}
