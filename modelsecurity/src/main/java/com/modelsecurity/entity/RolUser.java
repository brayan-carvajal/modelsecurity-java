package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol_user")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RolUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
