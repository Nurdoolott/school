package com.example.Spring.project2.mapper.impl;

import com.example.Spring.project2.dto.teacher.TeacherResponse;
import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.entities.Teacher;
import com.example.Spring.project2.mapper.TeacherMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherMapperImpl implements TeacherMapper {
    @Override
    public TeacherResponse toDto(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setName(teacher.getName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setEmail(teacher.getEmail());
        teacherResponse.setPassword(teacher.getPassword());
        teacherResponse.setRole(String.valueOf(teacher.getRole()));
        teacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        teacherResponse.setAge(teacher.getAge());
        return teacherResponse;
    }

    @Override
    public List<TeacherResponse> toDtoS(List<Teacher> all) {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for(Teacher teacher : all) {
            teacherResponses.add(toDto(teacher));
        }
        return teacherResponses;
    }
}
