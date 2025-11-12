package com.modelsecurity.cinema_module.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "screening_employee")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@SQLDelete(sql = "UPDATE screening_employee SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class ScreeningEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}