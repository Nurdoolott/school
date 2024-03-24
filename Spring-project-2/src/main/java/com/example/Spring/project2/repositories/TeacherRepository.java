package com.example.Spring.project2.repositories;

import com.example.Spring.project2.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    void deleteByEmail(String name);
    Optional<Teacher> findByEmail(String email);
}
