package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime registrationDate;
    private Boolean isDeleted = false;

    // Relación con Person
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    // Relación con RolUser
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RolUser> rolUser;
}
