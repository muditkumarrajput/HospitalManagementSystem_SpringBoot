package com.Practice.hospitalManagement.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Docter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private LocalDateTime specialization;

    @Column(nullable = false,unique = true, length = 100)
    private String email;

    @ManyToMany(mappedBy = "docters")
    private Set<Department> departmentSet = new HashSet<>();



}
