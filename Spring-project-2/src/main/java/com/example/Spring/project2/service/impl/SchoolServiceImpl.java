package com.example.Spring.project2.service.impl;

import com.example.Spring.project2.dto.school.SchoolRequest;
import com.example.Spring.project2.dto.school.SchoolResponse;
import com.example.Spring.project2.entities.School;
import com.example.Spring.project2.exception.NotFoundException;
import com.example.Spring.project2.mapper.SchoolMapper;
import com.example.Spring.project2.repositories.SchoolRepository;
import com.example.Spring.project2.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    @Override
    public List<SchoolResponse> getAll() {
        return schoolMapper.toDtoS(schoolRepository.findAll());
    }

    @Override
    public SchoolResponse findByName(String name) {
        Optional<School> school = schoolRepository.findByName(name);
        if(school.isEmpty()) {
            throw new NotFoundException("School not found", HttpStatus.NOT_FOUND);
        }
        return schoolMapper.toDto(school.get());
    }

    @Override
    public void updateByName(String name, SchoolRequest schoolRequest) {
        Optional<School> school = schoolRepository.findByName(name);
        if(school.isEmpty()) {
            throw new NotFoundException("School not found", HttpStatus.NOT_FOUND);
        }
        school.get().setName(schoolRequest.getName());
        school.get().setAddress(schoolRequest.getAddress());
        school.get().setPassword(schoolRequest.getPassword());
        schoolRepository.save(school.get());
    }

    @Override
    public void deleteByName(String name) {
        Optional<School> school = schoolRepository.findByName(name);
        if(school.isEmpty()) {
            throw new NotFoundException("School not found", HttpStatus.NOT_FOUND);
        }
        schoolRepository.deleteByName(name);
    }

    @Override
    public void register(SchoolRequest schoolRequest) {
        School school = new School();
        school.setName(schoolRequest.getName());
        school.setAddress(schoolRequest.getAddress());
        school.setPassword(schoolRequest.getPassword());
        schoolRepository.save(school);
    }
}
