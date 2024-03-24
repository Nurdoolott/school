package com.example.Spring.project2.entities;


import com.example.Spring.project2.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    @Transient
    private int age;
    private int grade;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "system_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school", referencedColumnName = "name")
    private School enrolStudentsToSchool;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_name", referencedColumnName = "email") // todo here should be the name instead of email!!!
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "student_name", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "subject_name", referencedColumnName = "name")
    )
    private Set<Subject> enrolSubjectsToStudents = new HashSet<>();
}
