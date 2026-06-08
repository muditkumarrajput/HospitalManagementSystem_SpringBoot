package com.Practice.hospitalManagement.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,length = 100)
    private String specialization;

    @Column(nullable = false,unique = true, length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departmentSet = new HashSet<>();



}
