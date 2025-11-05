package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "module")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<FormModule> formModule;
}
