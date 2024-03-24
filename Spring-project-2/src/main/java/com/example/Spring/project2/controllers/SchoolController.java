package com.example.Spring.project2.controllers;

import com.example.Spring.project2.dto.school.SchoolRequest;
import com.example.Spring.project2.dto.school.SchoolResponse;
import com.example.Spring.project2.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@AllArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("/getAll")
    public List<SchoolResponse> getAll() {
        return schoolService.getAll();
    }

    @GetMapping("/FindByName/{name}")
    public SchoolResponse findByName(@PathVariable String name) {
        return schoolService.findByName(name);
    }

    @PutMapping("/updateByName/{name}")
    public void updateByName(@PathVariable String name,@RequestBody SchoolRequest schoolRequest) {
        schoolService.updateByName(name, schoolRequest);
    }

    @DeleteMapping("/deleteByName/{name}")
    public void deleteByName(@PathVariable String name) {
        schoolService.deleteByName(name);
    }

    @PostMapping("/register")
    public void register(@RequestBody SchoolRequest schoolRequest) {
        schoolService.register(schoolRequest);
    }
}
