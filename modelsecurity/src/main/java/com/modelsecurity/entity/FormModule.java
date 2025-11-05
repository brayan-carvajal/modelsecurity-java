package com.modelsecurity.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "form_module")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class FormModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;
}
