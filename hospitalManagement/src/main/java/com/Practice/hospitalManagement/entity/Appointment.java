package com.Practice.hospitalManagement.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne //many appointment to one patient
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;


}
