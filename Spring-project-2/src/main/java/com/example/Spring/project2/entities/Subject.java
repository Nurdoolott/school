package com.example.Spring.project2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolSubjectsToStudents", cascade = CascadeType.ALL)
    private Set<Student> students;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolSubjectsToTeachers", cascade = CascadeType.ALL)
    private Set<Teacher> teachers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "School",referencedColumnName = "name") // todo here is the warn!
    private School enrolSubjectToSchool;
}
