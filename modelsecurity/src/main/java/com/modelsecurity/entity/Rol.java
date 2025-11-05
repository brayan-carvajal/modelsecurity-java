package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rol")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Boolean isDeleted = false;

    // Un rol puede pertenecer a muchos usuarios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<RolUser> rolUser;

    // Un rol puede tener permisos sobre varios formularios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<RolFormPermit> rolFormPermit;
}
