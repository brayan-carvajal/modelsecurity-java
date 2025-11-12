package com.modelsecurity.security_module.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "rol")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@SQLDelete(sql = "UPDATE rol SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    // Un rol puede pertenecer a muchos usuarios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<RolUser> rolUser;

    // Un rol puede tener permisos sobre varios formularios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<RolFormPermit> rolFormPermit;
}
