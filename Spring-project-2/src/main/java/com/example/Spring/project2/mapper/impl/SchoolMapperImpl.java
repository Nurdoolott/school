package com.example.Spring.project2.mapper.impl;

import com.example.Spring.project2.dto.school.SchoolResponse;
import com.example.Spring.project2.entities.School;
import com.example.Spring.project2.mapper.SchoolMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolMapperImpl implements SchoolMapper {
    @Override
    public SchoolResponse toDto(School school) {
        SchoolResponse schoolResponse = new SchoolResponse();
        schoolResponse.setId(school.getId());
        schoolResponse.setName(school.getName());
        schoolResponse.setAddress(school.getAddress());
        schoolResponse.setPassword(school.getPassword());
        return schoolResponse;
    }

    @Override
    public List<SchoolResponse> toDtoS(List<School> all) {
        List<SchoolResponse> schoolResponses = new ArrayList<>();
        for (School school : all) {
            schoolResponses.add(toDto(school));
        }
        return schoolResponses;
    }
}
