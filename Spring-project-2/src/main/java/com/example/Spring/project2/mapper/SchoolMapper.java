package com.example.Spring.project2.mapper;

import com.example.Spring.project2.dto.school.SchoolResponse;
import com.example.Spring.project2.entities.School;

import java.util.List;

public interface SchoolMapper {
    SchoolResponse toDto(School school);
    List<SchoolResponse> toDtoS(List<School> all);
}
