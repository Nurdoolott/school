package com.example.Spring.project2.service.impl;

import com.example.Spring.project2.dto.student.StudentRequest;
import com.example.Spring.project2.dto.student.StudentResponse;
import com.example.Spring.project2.entities.School;
import com.example.Spring.project2.entities.Student;
import com.example.Spring.project2.entities.Subject;
import com.example.Spring.project2.entities.Teacher;
import com.example.Spring.project2.exception.NotFoundException;
import com.example.Spring.project2.mapper.StudentMapper;
import com.example.Spring.project2.repositories.SchoolRepository;
import com.example.Spring.project2.repositories.StudentRepository;
import com.example.Spring.project2.repositories.SubjectRepository;
import com.example.Spring.project2.repositories.TeacherRepository;
import com.example.Spring.project2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final SchoolRepository schoolRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<StudentResponse> getAll() {
        return studentMapper.toDtoS(studentRepository.findAll());
    }

    @Override
    public StudentResponse findByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        isContains(student, email);
        return studentMapper.toDto(student.get());
    }

    @Override
    public void updateByEmail(String email, StudentRequest studentRequest) {
        Optional<Student> student = studentRepository.findByEmail(email);
        isContains(student, email);
        student.get().setName(studentRequest.getName());
        student.get().setLastName(studentRequest.getLastName());
        student.get().setEmail(studentRequest.getEmail());
        student.get().setPassword(studentRequest.getPassword());
        student.get().setDateOfBirth(studentRequest.getDateOfBirth());
        student.get().setGrade(studentRequest.getGrade());
        studentRepository.save(student.get());
    }

    @Override
    public void deleteByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        isContains(student, email);
        studentRepository.deleteByEmail(email);
    }

    @Override
    public void assignStudentToSchool(String studentEmail, String schoolName) {
        Optional<Student> student = studentRepository.findByEmail(studentEmail);
        isContains(student, studentEmail);

        Optional<School> school = schoolRepository.findByName(schoolName);
        if(school.isEmpty()) {
            throw new NotFoundException("School not found! ", HttpStatus.NOT_FOUND);
        }
        student.get().setEnrolStudentsToSchool(school.get());
        studentRepository.save(student.get());
    }

    @Override
    public void assignSubjectToStudent(String studentEmail, String subjectName) {
        Optional<Student> student = studentRepository.findByEmail(studentEmail);
        isContains(student, studentEmail);

        Optional<Subject> subject = subjectRepository.findByName(subjectName);
        if(subject.isEmpty()) {
            throw new NotFoundException(subjectName + " not found! ", HttpStatus.NOT_FOUND);
        }
        Set<Subject> enrolSubject = student.get().getEnrolSubjectsToStudents();
        enrolSubject.add(subject.get());
        student.get().setEnrolSubjectsToStudents(enrolSubject);
        studentRepository.save(student.get());
        // todo: Is this relationship correct???
    }

    @Override
    public void assignStudentsToTeacher(String studentEmail, String teacherEmail) {
        Optional<Student> student = studentRepository.findByEmail(studentEmail);
        isContains(student, studentEmail);

        Optional<Teacher> teacher = teacherRepository.findByEmail(teacherEmail);
        if(teacher.isEmpty()) {
            throw new NotFoundException(teacherEmail + " not found", HttpStatus.NOT_FOUND);
        }
        student.get().setTeacher(teacher.get());
        studentRepository.save(student.get());
    }

    private void isContains(Optional<Student> student, String studentEmail) {
        if(student.isEmpty()) {
            throw new NotFoundException(studentEmail + " not found! ", HttpStatus.NOT_FOUND);
        }
    }
}
