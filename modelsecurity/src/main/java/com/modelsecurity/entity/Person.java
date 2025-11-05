package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String documentType;
    private String document;
    private LocalDate dateBorn;
    private String phoneNumber;
    private String gender;
    private String personExter;
    private String epsId;
    private String secondLastName;
    private String middleName;
    private Integer cityId;
    private Boolean isDeleted = false;

    // Relación 1:1 con User
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
