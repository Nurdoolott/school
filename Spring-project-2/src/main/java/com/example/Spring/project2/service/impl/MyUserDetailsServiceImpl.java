package com.example.Spring.project2.service.impl;

import com.example.Spring.project2.dto.user.UserRequest;
import com.example.Spring.project2.dto.user.UserResponse;
import com.example.Spring.project2.entities.Student;
import com.example.Spring.project2.entities.Teacher;
import com.example.Spring.project2.entities.User;
import com.example.Spring.project2.enums.Role;
import com.example.Spring.project2.exception.BadRequestException;
import com.example.Spring.project2.exception.NotFoundException;
import com.example.Spring.project2.mapper.UserMapper;
import com.example.Spring.project2.repositories.StudentRepository;
import com.example.Spring.project2.repositories.TeacherRepository;
import com.example.Spring.project2.repositories.UserRepository;
import com.example.Spring.project2.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    @Override
    public List<UserResponse> getAll() {

        return userMapper.toDtoS(userRepository.findAll());
    }

    @Override
    public UserResponse findByEmail(String userEmail) {
//        User actionUser = getUserNameFromToken(token);
//        System.out.println(actionUser.getName());
        Optional<User> user = userRepository.findByEmail(userEmail);
        if(user.isEmpty()) {
            throw new NotFoundException("user not found with email: " + userEmail, HttpStatus.BAD_REQUEST);
        }
        return userMapper.toDto(user.get());
    }

    @Override
    public User getUserNameFromToken(String token) {
        return null;
    }

    @Override
    public void updateByEmail(String userEmail, UserRequest userRequest) {
        Optional<User> user = userRepository.findByEmail(userEmail);
        if(user.isEmpty()) {
            throw new NotFoundException("user not found with email: " + userEmail, HttpStatus.BAD_REQUEST);
        }
        user.get().setName(userRequest.getName());
        user.get().setLastName(userRequest.getEmail());
        user.get().setEmail(userRequest.getEmail());
        user.get().setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.get().setRole(Role.valueOf(userRequest.getRole()));
        user.get().setDateOfBirth(userRequest.getDateOfBirth());
        userRepository.save(user.get());
    }

    @Override
    public void deleteByEmail(String userEmail) {
        if(userRepository.findByEmail(userEmail).isPresent()) {
            throw new NotFoundException("user not found with email: " + userEmail, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteByEmail(userEmail);
    }

    @Override
    public void register(UserRequest userRequest) {
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new BadRequestException("user with this email is already exist!: " + userRequest.getEmail());
        }
        User user = new User();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.valueOf(userRequest.getRole()));
        user.setDateOfBirth(userRequest.getDateOfBirth());

        if(userRequest.getRole().equals("TEACHER")) {
            Teacher teacher = new Teacher();
            teacher.setName(userRequest.getName());
            teacher.setLastName(userRequest.getLastName());
            teacher.setEmail(userRequest.getEmail());
            teacher.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            teacher.setDateOfBirth(userRequest.getDateOfBirth());
            teacher.setRole(Role.valueOf(userRequest.getRole()));
            teacher.setUser(user);
            teacherRepository.save(teacher);

            user.setTeacher(teacher);
            userRepository.save(user);
        } else if (userRequest.getRole().equals("STUDENT")) {
            Student student = new Student();
            student.setName(userRequest.getName());
            student.setLastName(userRequest.getLastName());
            student.setEmail(userRequest.getEmail());
            student.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            student.setDateOfBirth(userRequest.getDateOfBirth());
            student.setRole(Role.valueOf(userRequest.getRole()));
            student.setUser(user);
            studentRepository.save(student);

            user.setStudent(student);
            userRepository.save(user);
        }
    }
}
