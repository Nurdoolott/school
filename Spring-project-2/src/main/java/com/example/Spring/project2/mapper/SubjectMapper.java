package com.example.Spring.project2.mapper;

import com.example.Spring.project2.dto.subject.SubjectResponse;
import com.example.Spring.project2.entities.Subject;

import java.util.List;

public interface SubjectMapper {
    SubjectResponse toDto(Subject subject);
    List<SubjectResponse> toDtoS(List<Subject> all);
}
