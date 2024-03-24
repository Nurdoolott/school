package com.example.Spring.project2.service;


import com.example.Spring.project2.dto.teacher.TeacherRequest;
import com.example.Spring.project2.dto.teacher.TeacherResponse;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAll();
    TeacherResponse findByEmail(String email);
    void updateByEmail(String email, TeacherRequest teacherRequest);
    void deleteByEmail(String email);
    void assignTeacherToSchool(String teacherEmail, String schoolName);
    void assignTeachersToSubjects(String teacherEmail, String subjectName);
}
