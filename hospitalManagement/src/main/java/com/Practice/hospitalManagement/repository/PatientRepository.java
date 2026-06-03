package com.Practice.hospitalManagement.repository;


import com.Practice.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.entity.type.BloodGroupType;
//import org.hibernate.query.Page;
//  ADD THIS IMPORT
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
//import java.awt.print.Pageable;
import java.time.LocalDate;
//  ADD THIS
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);


    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);



//    @Query("select new com.Practice.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup," + "Count(p))from Patient p group by p.bloodGroup")
////    List<Object[]> countEachBloodGroupType();
//    List<BloodGroupCountResponseEntity> countEachBloodGroupType;

    @Query("SELECT new com.Practice.hospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, COUNT(p)) " +
            "FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);



}
