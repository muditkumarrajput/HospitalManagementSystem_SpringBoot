package com.Practice.hospitalManagement.entity;


import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToOne
    private Docter headDocter;

    @ManyToMany
    @JoinTable(name = "My_dpt_docters", joinColumns = @JoinColumn(name = "dpt_id"),inverseJoinColumns = @JoinColumn(name = "docter_id"))
    private Set<Docter> docters = new HashSet<>();


}
