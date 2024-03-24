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

@Entity
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "system_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school", referencedColumnName = "name")
    private School enrolTeachersToSchool;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
//    @JoinColumn(name = "students", referencedColumnName = "name")
    private List<Student> students;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subject_enrolled",
            joinColumns = @JoinColumn(name = "teacher", referencedColumnName = "name"),
            inverseJoinColumns = @JoinColumn(name = "subject", referencedColumnName = "name")
    )
    private Set<Subject> enrolSubjectsToTeachers = new HashSet<>();
}
