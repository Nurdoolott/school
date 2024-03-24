package com.example.Spring.project2.service.impl;

import com.example.Spring.project2.dto.subject.SubjectRequest;
import com.example.Spring.project2.dto.subject.SubjectResponse;
import com.example.Spring.project2.entities.School;
import com.example.Spring.project2.entities.Subject;
import com.example.Spring.project2.exception.NotFoundException;
import com.example.Spring.project2.mapper.SubjectMapper;
import com.example.Spring.project2.repositories.SchoolRepository;
import com.example.Spring.project2.repositories.SubjectRepository;
import com.example.Spring.project2.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final SchoolRepository schoolRepository;

    @Override
    public List<SubjectResponse> getAll() {
        return subjectMapper.toDtoS(subjectRepository.findAll());
    }

    @Override
    public SubjectResponse findByName(String name) {
        Optional<Subject> subject = subjectRepository.findByName(name);
        if(subject.isEmpty()) {
            throw new NotFoundException("Subject not found! ", HttpStatus.NOT_FOUND);
        }
        return subjectMapper.toDto(subject.get());
    }

    @Override
    public void updateByName(String name, SubjectRequest subjectRequest) {
        Optional<Subject> subject = subjectRepository.findByName(name);
        if(subject.isEmpty()) {
            throw new NotFoundException("Subject not found! ", HttpStatus.NOT_FOUND);
        }
        subject.get().setName(subjectRequest.getName());
        subjectRepository.save(subject.get());
    }

    @Override
    public void deleteByName(String name) {
        Optional<Subject> subject = subjectRepository.findByName(name);
        if(subject.isEmpty()) {
            throw new NotFoundException("Subject not found! ", HttpStatus.NOT_FOUND);
        }
        subjectRepository.deleteByName(name);
    }

    @Override
    public void register(SubjectRequest subjectRequest) {
        Subject subject = new Subject();
        subject.setName(subjectRequest.getName());
        subjectRepository.save(subject);
    }

    @Override
    public void assignSubjectToSchool(String subjectName, String schoolName) {
        Optional<Subject> subject = subjectRepository.findByName(subjectName);
        if(subject.isEmpty()) {
            throw new NotFoundException("Subject not found! ", HttpStatus.NOT_FOUND);
        }

        Optional<School> school = schoolRepository.findByName(schoolName);
        if(school.isEmpty()) {
            throw new NotFoundException("School not found! ", HttpStatus.NOT_FOUND);
        }
        subject.get().setEnrolSubjectToSchool(school.get());
        subjectRepository.save(subject.get());
    }
}
