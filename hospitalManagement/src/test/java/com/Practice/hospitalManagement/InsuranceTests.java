package com.Practice.hospitalManagement;

import com.Practice.hospitalManagement.entity.Appointment;
import com.Practice.hospitalManagement.entity.Insurance;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.service.AppointmentService;
import com.Practice.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional // Rolls back database changes after each test runs
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testInsurance() {
        // Given
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        // When
        Patient patient = insuranceService.assignInsurancePatient(insurance, 1L);
        System.out.println("Assigned Patient: " + patient);

//        // Then
//        assertNotNull(patient);
        var newPatient = insuranceService.disassociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void testCreateAppointment() {
        // Given
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("Cancer")
                .build();

        // When creating appointment for Patient 1L and Doctor 2L
        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
        System.out.println("Created Appointment: " + newAppointment);

        // Then
        assertNotNull(newAppointment);

        // When reassigning to Doctor 3L
        Appointment updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
        System.out.println("Updated Appointment: " + updatedAppointment);

        // Then
        assertNotNull(updatedAppointment);
    }
}