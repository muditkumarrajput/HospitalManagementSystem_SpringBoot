//
//package com.Practice.hospitalManagement;
//
//import com.Practice.hospitalManagement.entity.Appointment;
//import com.Practice.hospitalManagement.entity.Insurance;
//import com.Practice.hospitalManagement.entity.Doctor;
//import com.Practice.hospitalManagement.entity.Patient;
//import com.Practice.hospitalManagement.repository.DoctorRepository;
//import com.Practice.hospitalManagement.repository.PatientRepository;
//import com.Practice.hospitalManagement.service.AppointmentService;
//import com.Practice.hospitalManagement.service.InsuranceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@SpringBootTest
//@Transactional
//public class InsuranceTests {
//
//    @Autowired
//    private InsuranceService insuranceService;
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    @BeforeEach
//    public void setUp() {
//        // Clear out records between tests to keep the execution environment isolated
//        doctorRepository.deleteAll();
//        patientRepository.deleteAll();
//
//        // 1. Persist default Doctor record with ALL required schema fields
//        Doctor defaultDoctor = Doctor.builder()
//                .name("Dr. Smith")
//                .email("smith.test@hospital.com")
//                .specialization("Cardiology") // Added to resolve the new PropertyValueException
//                .build();
//        doctorRepository.save(defaultDoctor);
//
//        // 2. Persist default Patient record with required schema fields
//        Patient defaultPatient = Patient.builder()
//                .name("John Doe")
//                .email("john.test@example.com")
//                .birthDate(LocalDate.of(1995, 5, 20))
//                .build();
//        patientRepository.save(defaultPatient);
//    }
//
//    @Test
//    public void testInsurance() {
//
//        Insurance insurance = Insurance.builder()
//                .policyNumber("HDFC_1234")
//                .provider("HDFC")
//                .validUntil(LocalDate.of(2030, 12, 12))
//                .build();
//
//        // Dynamically gets the valid generated ID from the setup block
//        Patient patientRecord = patientRepository.findAll().get(0);
//
//        Patient patient = insuranceService.assignInsurancePatient(insurance, patientRecord.getId());
//
//        System.out.println(patient);
//    }
//
//    @Test
//    public void testCreateAppointment() {
//
//        Appointment appointment = Appointment.builder()
//                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0))
//                .reason("Cough")
//                .build();
//
//        // Dynamically gets the valid database objects populated in the setup block
//        Doctor doctor = doctorRepository.findAll().get(0);
//        Patient patient = patientRepository.findAll().get(0);
//
//        var newAppointment = appointmentService.createNewAppointment(
//                appointment,
//                doctor.getId(),
//                patient.getId()
//        );
//
//        System.out.println(newAppointment);
//    }
//}

package com.Practice.hospitalManagement;

import com.Practice.hospitalManagement.entity.Appointment;
import com.Practice.hospitalManagement.entity.Insurance;
import com.Practice.hospitalManagement.entity.Doctor;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.repository.DoctorRepository;
import com.Practice.hospitalManagement.repository.PatientRepository;
import com.Practice.hospitalManagement.repository.AppointmentRepository; // Added import
import com.Practice.hospitalManagement.service.AppointmentService;
import com.Practice.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals; // Added for testing confirmation

@SpringBootTest
@Transactional
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository; // Added to check table counts

    @BeforeEach
    public void setUp() {
        // Clear out records between tests to keep the execution environment isolated
        appointmentRepository.deleteAll(); // Clean this up first to avoid reference violations
        doctorRepository.deleteAll();
        patientRepository.deleteAll();

        // 1. Persist default Doctor record with ALL required schema fields
        Doctor defaultDoctor = Doctor.builder()
                .name("Dr. Smith")
                .email("smith.test@hospital.com")
                .specialization("Cardiology")
                .build();
        doctorRepository.save(defaultDoctor);

        // 2. Persist default Patient record with required schema fields
        Patient defaultPatient = Patient.builder()
                .name("John Doe")
                .email("john.test@example.com")
                .birthDate(LocalDate.of(1995, 5, 20))
                .build();
        patientRepository.save(defaultPatient);
    }

    @Test
    public void testInsurance() {

        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        Patient patientRecord = patientRepository.findAll().get(0);

        Patient patient = insuranceService.assignInsurancePatient(insurance, patientRecord.getId());

        System.out.println(patient);
    }

    @Test
    @Commit
    public void testCreateAppointment() {

        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0))
                .reason("Cough")
                .build();

        Doctor doctor = doctorRepository.findAll().get(0);
        Patient patient = patientRepository.findAll().get(0);

        var newAppointment = appointmentService.createNewAppointment(
                appointment,
                doctor.getId(),
                patient.getId()
        );

        System.out.println(newAppointment);

        // --- VERIFICATION STEP ---
        // This programmatically checks that the table has an entry right now.
        long totalAppointments = appointmentRepository.count();
        System.out.println("Active appointments in transaction: " + totalAppointments);

        assertEquals(1, totalAppointments, "The appointment entry should be saved in the database context!");
    }
}