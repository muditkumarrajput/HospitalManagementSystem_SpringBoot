package com.Practice.hospitalManagement.repository;

import com.Practice.hospitalManagement.entity.Docter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocterRepository extends JpaRepository<Docter, Long> {
}