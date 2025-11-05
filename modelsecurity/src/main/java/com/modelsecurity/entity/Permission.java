package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "permission")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL)
    private List<RolFormPermit> rolFormPermit;
}
