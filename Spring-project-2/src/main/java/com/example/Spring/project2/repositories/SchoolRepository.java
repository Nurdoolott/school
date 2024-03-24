package com.example.Spring.project2.repositories;

import com.example.Spring.project2.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    void deleteByName(String name);
    Optional<School> findByName(String name);
}
