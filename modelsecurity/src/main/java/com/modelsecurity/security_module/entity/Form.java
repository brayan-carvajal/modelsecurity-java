package com.modelsecurity.security_module.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "form")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@SQLDelete(sql = "UPDATE form SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormModule> formModule;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<RolFormPermit> rolFormPermit;
}
