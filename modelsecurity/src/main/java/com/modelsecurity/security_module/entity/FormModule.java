package com.modelsecurity.security_module.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "form_module")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@SQLDelete(sql = "UPDATE form_module SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class FormModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;
}
