package com.example.Spring.project2.controllers;

import com.example.Spring.project2.dto.teacher.TeacherRequest;
import com.example.Spring.project2.dto.teacher.TeacherResponse;
import com.example.Spring.project2.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/getAll")
    public List<TeacherResponse> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/findByEmail/{email}")
    public TeacherResponse findByEmail(@PathVariable String email) {
        return teacherService.findByEmail(email);
    }

    @PutMapping("/updateByEmail/{email}")
    public String updateByEmail(@PathVariable String email,@RequestBody TeacherRequest teacherRequest) {
        teacherService.updateByEmail(email, teacherRequest);
        return "Teacher successfully updated!";
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public String deleteByEmail(@PathVariable String email) {
        teacherService.deleteByEmail(email);
        return "Teacher successfully deleted!";
    }

    @PutMapping("{teacherEmail}/school/{schoolName}")
    public String assignTeacherToSchool(@PathVariable String teacherEmail,@PathVariable String schoolName) {
        teacherService.assignTeacherToSchool(teacherEmail, schoolName);
        return "Teacher successfully assign to School";
    }

    @PutMapping("/{teacherEmail}/subject/{subjectName}")
    public String assignTeachersToSubjects(@PathVariable String teacherEmail,@PathVariable String subjectName) {
        teacherService.assignTeachersToSubjects(teacherEmail, subjectName);
        return "Teacher and Subject successfully merged!";
    }
}
