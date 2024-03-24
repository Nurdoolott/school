package com.example.Spring.project2.service;

import com.example.Spring.project2.dto.school.SchoolRequest;
import com.example.Spring.project2.dto.school.SchoolResponse;
import com.example.Spring.project2.entities.School;

import java.util.List;

public interface SchoolService {
    List<SchoolResponse> getAll();
    SchoolResponse findByName(String name);
    void updateByName(String name, SchoolRequest schoolRequest);
    void deleteByName(String name);
    void register(SchoolRequest schoolRequest);
}
