package com.example.Spring.project2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "school_table")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;
    private String address;
    private String password;

    @OneToMany(mappedBy = "enrolStudentsToSchool", cascade = CascadeType.ALL)
//    @JoinColumn(name = "students", referencedColumnName = "name")
    private List<Student> students;

    @OneToMany(mappedBy = "enrolTeachersToSchool", cascade = CascadeType.ALL)
//    @JoinColumn(name = "teachers", referencedColumnName = "name")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "enrolSubjectToSchool", cascade = CascadeType.ALL)
//    @JoinColumn(name = "subject", referencedColumnName = "name")
    private Set<Subject> subject = new HashSet<>();
}
