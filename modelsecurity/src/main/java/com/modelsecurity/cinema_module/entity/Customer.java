package com.modelsecurity.cinema_module.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "customer")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@SQLDelete(sql = "UPDATE customer SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;
}