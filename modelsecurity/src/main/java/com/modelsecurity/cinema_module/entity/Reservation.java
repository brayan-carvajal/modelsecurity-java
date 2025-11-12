package com.modelsecurity.cinema_module.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@SQLDelete(sql = "UPDATE reservation SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    private String status;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;
}