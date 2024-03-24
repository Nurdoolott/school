package com.example.Spring.project2.service.impl;

import com.example.Spring.project2.dto.teacher.TeacherRequest;
import com.example.Spring.project2.dto.teacher.TeacherResponse;
import com.example.Spring.project2.entities.School;
import com.example.Spring.project2.entities.Subject;
import com.example.Spring.project2.entities.Teacher;
import com.example.Spring.project2.exception.NotFoundException;
import com.example.Spring.project2.mapper.TeacherMapper;
import com.example.Spring.project2.repositories.SchoolRepository;
import com.example.Spring.project2.repositories.SubjectRepository;
import com.example.Spring.project2.repositories.TeacherRepository;
import com.example.Spring.project2.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherMapper teacherMapper;
    private final SubjectRepository subjectRepository;

    @Override
    public List<TeacherResponse> getAll() {
        return teacherMapper.toDtoS(teacherRepository.findAll());
    }

    @Override
    public TeacherResponse findByEmail(String email) {
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if(teacher.isEmpty()) {
            throw new NotFoundException("Teacher not found! ", HttpStatus.NOT_FOUND);
        }
        return teacherMapper.toDto(teacher.get());
    }

    @Override
    public void updateByEmail(String email, TeacherRequest teacherRequest) {
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if(teacher.isEmpty()) {
            throw new NotFoundException("Teacher not found! ", HttpStatus.NOT_FOUND);
        }
        teacher.get().setName(teacherRequest.getName());
        teacher.get().setLastName(teacherRequest.getLastName());
        teacher.get().setEmail(teacherRequest.getEmail());
        teacher.get().setPassword(teacherRequest.getPassword());
        teacher.get().setDateOfBirth(teacherRequest.getDateOfBirth());
        teacherRepository.save(teacher.get());
    }

    @Override
    public void deleteByEmail(String email) {
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if(teacher.isEmpty()) {
            throw new NotFoundException("Teacher not found! ", HttpStatus.NOT_FOUND);
        }
        teacherRepository.deleteByEmail(email);
    }

    @Override
    public void assignTeacherToSchool(String teacherEmail, String schoolName) {
        Optional<Teacher> teacher = teacherRepository.findByEmail(teacherEmail);
        if(teacher.isEmpty()) {
            throw new NotFoundException("Teacher not found! ", HttpStatus.NOT_FOUND);
        }

        Optional<School> school = schoolRepository.findByName(schoolName);
        if(school.isEmpty()) {
            throw new NotFoundException("School not found! ", HttpStatus.NOT_FOUND);
        }
        teacher.get().setEnrolTeachersToSchool(school.get());
        teacherRepository.save(teacher.get());
        // todo: Do we have to save it to the school repository???
    }

    @Override
    public void assignTeachersToSubjects(String teacherEmail, String subjectName) {
        Optional<Teacher> teacher = teacherRepository.findByEmail(teacherEmail);
        if(teacher.isEmpty()) {
            throw new NotFoundException("Teacher not found! ", HttpStatus.NOT_FOUND);
        }

        Optional<Subject> subject =  subjectRepository.findByName(subjectName);
        if(subject.isEmpty()) {
            throw new NotFoundException(subjectName + " not found", HttpStatus.NOT_FOUND);
        }
        Set<Subject> enrolSubject = teacher.get().getEnrolSubjectsToTeachers();
        enrolSubject.add(subject.get());
        teacher.get().setEnrolSubjectsToTeachers(enrolSubject);
        teacherRepository.save(teacher.get());
    }
}
