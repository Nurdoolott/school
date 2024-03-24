package com.example.Spring.project2.service;

import com.example.Spring.project2.dto.subject.SubjectRequest;
import com.example.Spring.project2.dto.subject.SubjectResponse;

import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getAll();
    SubjectResponse findByName(String name);
    void updateByName(String name, SubjectRequest subjectRequest);
    void deleteByName(String name);
    void register (SubjectRequest subjectRequest);
    void assignSubjectToSchool(String subjectName, String schoolName);
}
