//package com.Practice.hospitalManagement.entity;
//
//
//import com.Practice.hospitalManagement.entity.type.BloodGroupType;
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@ToString
//
//@Getter
//@Setter
//
//@Builder
//@Table(
//        name = "patient",
//        uniqueConstraints = {
////                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
//                @UniqueConstraint(name = "unique_patient_name_birthdate",columnNames = {"name","birthDate"})
//
//
//        },
//        indexes = {
//                @Index(name = "idx_patient_birth_date",columnList = "birthDate")
//        }
//)
//
//public class Patient {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false,length = 40)
//    private String name;
//
////    @ToString.Exclude
//    private LocalDate birthDate;
//
//
//    @Column(unique = true,nullable = false)
//    private String email;
//
//    private String gender;
//
//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createAt;
//
//    @Enumerated(EnumType.STRING)
//    private BloodGroupType bloodGroup;
//
//    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "patient_Insurance_Id")
//    private Insurance insurance;
////    private String bloodGroup;
//
////    @Override
////    public String toString() {
////        return "Patient{" +
////                "id=" + id +
////                ", name='" + name + '\'' +
////                ", birthDate=" + birthDate +
////                ", email='" + email + '\'' +
////                ", gender='" + gender + '\'' +
////                '}';
////    }
//
//    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE},orphanRemoval = true)
//    @ToString.Exclude
//    private List<Appointment> appointments = new ArrayList<>();
//
//}

package com.Practice.hospitalManagement.entity;

import com.Practice.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;  // 1. Import added
import lombok.AllArgsConstructor; // 2. Import added
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor   // 3. Added to give Hibernate the no-args constructor it requires
@AllArgsConstructor  // 4. Added to give Lombok's Builder the full constructor it needs
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name","birthDate"})
        },
        indexes = {
                @Index(name = "idx_patient_birth_date", columnList = "birthDate")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "patient_Insurance_Id")
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default // 5. Added to preserve your default ArrayList assignment when using a builder
    private List<Appointment> appointments = new ArrayList<>();
}