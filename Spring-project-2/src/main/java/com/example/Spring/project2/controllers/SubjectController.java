package com.example.Spring.project2.controllers;

import com.example.Spring.project2.dto.subject.SubjectRequest;
import com.example.Spring.project2.dto.subject.SubjectResponse;
import com.example.Spring.project2.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/getAll")
    public List<SubjectResponse> getAll() {
        return subjectService.getAll();
    }

    @GetMapping("/findByName/{name}")
    public SubjectResponse findByName(@PathVariable String name) {
        return subjectService.findByName(name);
    }

    @PutMapping("/updateByName/{name}")
    public String updateByName(@PathVariable String name,@RequestBody SubjectRequest subjectRequest) {
        subjectService.updateByName(name, subjectRequest);
        return "Subject successfully updated!";
    }

    @DeleteMapping("/deleteByName/{name}")
    public String deleteByName(@PathVariable String name) {
        subjectService.deleteByName(name);
        return "Subject successfully deleted!";
    }

    @PostMapping("/register")
    public String register(@RequestBody SubjectRequest subjectRequest) {
        subjectService.register(subjectRequest);
        return "Subject successfully registered!";
    }

    @PutMapping("{subjectName}/school/{schoolName}")
    public String assignSubjectToSchool(@PathVariable String subjectName,@PathVariable String schoolName) {
        subjectService.assignSubjectToSchool(subjectName, schoolName);
        return "Subject successfully assign to " + schoolName;
    }
}
