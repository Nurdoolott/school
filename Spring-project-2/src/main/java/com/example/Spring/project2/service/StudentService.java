package com.example.Spring.project2.service;

import com.example.Spring.project2.dto.student.StudentRequest;
import com.example.Spring.project2.dto.student.StudentResponse;
import com.example.Spring.project2.entities.Student;
import com.example.Spring.project2.entities.Subject;


import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();
    StudentResponse findByEmail(String email);
    void updateByEmail(String email, StudentRequest studentRequest);
    void deleteByEmail(String email);
    void assignStudentToSchool(String studentEmail, String schoolName);
    void assignSubjectToStudent(String studentEmail, String subjectName);
    void assignStudentsToTeacher(String studentEmail, String teacherEmail);
}
