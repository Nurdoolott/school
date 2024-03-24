package com.example.Spring.project2.controllers;

import com.example.Spring.project2.dto.student.StudentRequest;
import com.example.Spring.project2.dto.student.StudentResponse;
import com.example.Spring.project2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAll")
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/findByEmail/{email}")
    public StudentResponse findByEmail(@PathVariable String email) {
        return studentService.findByEmail(email);
    }

    @PutMapping("/updateByEmail/{email}")
    public String updateByEmail(@PathVariable String email,@RequestBody StudentRequest studentRequest) {
        studentService.updateByEmail(email, studentRequest);
        return "Student successfully updated!";
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public String deleteByEmail(@PathVariable String email) {
        studentService.deleteByEmail(email);
        return "Student successfully deleted!";
    }

    @PutMapping("/{studentEmail}/school/{schoolName}")
    public String assignStudentToSchool(@PathVariable String studentEmail,@PathVariable String schoolName) {
        studentService.assignStudentToSchool(studentEmail, schoolName);
        return "Student successfully assign to " + schoolName;
    }

    @PutMapping("/{studentEmail}/subject/{subjectName}")
    public String assignSubjectToStudent(@PathVariable String studentEmail,@PathVariable String subjectName) {
        studentService.assignSubjectToStudent(studentEmail, subjectName);
        return "Student and subject successfully merged!";
    }

    @PutMapping("/{studentEmail}/teacher/{teacherEmail}")
    public String assignStudentsToTeacher(@PathVariable String studentEmail,@PathVariable String teacherEmail) {
        studentService.assignStudentsToTeacher(studentEmail, teacherEmail);
        return "Student successfully assign to Teacher";
    }
}
