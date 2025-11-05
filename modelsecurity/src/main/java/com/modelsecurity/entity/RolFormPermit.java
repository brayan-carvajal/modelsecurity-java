package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol_form_permit")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RolFormPermit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
}
