package com.example.Spring.project2.mapper.impl;

import com.example.Spring.project2.dto.subject.SubjectResponse;
import com.example.Spring.project2.entities.Subject;
import com.example.Spring.project2.mapper.SubjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapperImpl implements SubjectMapper {
    @Override
    public SubjectResponse toDto(Subject subject) {
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(subject.getId());
        subjectResponse.setName(subject.getName());
        return subjectResponse;
    }

    @Override
    public List<SubjectResponse> toDtoS(List<Subject> all) {
        List<SubjectResponse> subjectResponses = new ArrayList<>();
        for(Subject subject : all) {
            subjectResponses.add(toDto(subject));
        }
        return subjectResponses;
    }
}
