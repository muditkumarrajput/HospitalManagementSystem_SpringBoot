//package com.Practice.hospitalManagement.service;
//
//import com.Practice.hospitalManagement.entity.Appointment;
//import com.Practice.hospitalManagement.entity.Docter;
//import com.Practice.hospitalManagement.entity.Patient;
//import com.Practice.hospitalManagement.repository.AppointmentRepository;
//import com.Practice.hospitalManagement.repository.DocterRepository;
//import com.Practice.hospitalManagement.repository.PatientRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.print.Doc;
//
//@Service
//@RequiredArgsConstructor
//public class AppointmentService {
//
//    private final AppointmentRepository appointmentRepository;
//    private final DocterRepository docterRepository;
//    private final PatientRepository patientRepository;
//
//    @Transactional
//    public Appointment createNewAppointment(Appointment appointment, Long docterId, Long patientId){
//        Docter docter = docterRepository.findById(docterId).orElseThrow();
//        Patient patient = patientRepository.findById(patientId).orElseThrow();
//
//        if(appointment.getId()!= null) throw new IllegalArgumentException("Appointment should have the things.");
//
//        appointment.setPatient(patient);
//        appointment.setDocter(docter);
//
//        patient.getAppointments().add(appointment);
//
//        return appointmentRepository.save(appointment);
//    }
//}
package com.Practice.hospitalManagement.service;

import com.Practice.hospitalManagement.entity.Appointment;
import com.Practice.hospitalManagement.entity.Doctor;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.repository.AppointmentRepository;
import com.Practice.hospitalManagement.repository.DoctorRepository;
import com.Practice.hospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment,
                                            Long doctorId,
                                            Long patientId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientId));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }
}