package com.Practice.hospitalManagement.service;


import com.Practice.hospitalManagement.entity.Insurance;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.repository.InsuranceRepository;
import com.Practice.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    //Assign insurance to the patient
    @Transactional
    public Patient assignInsurancePatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(insurance); //main line
        insurance.setPatient(patient); //bidirectional consistency maintain

        return patient;

    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id:" + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
