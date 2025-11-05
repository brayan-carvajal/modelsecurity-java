package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "form")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormModule> formModule;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<RolFormPermit> rolFormPermit;
}
