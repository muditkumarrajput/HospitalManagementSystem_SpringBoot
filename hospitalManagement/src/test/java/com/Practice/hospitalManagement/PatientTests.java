package com.Practice.hospitalManagement;

import com.Practice.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.entity.type.BloodGroupType;
import com.Practice.hospitalManagement.repository.PatientRepository;
import com.Practice.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PatientTests {


    @Autowired
    private PatientRepository patientRepository;

//    @Test
    public void testPatientRepository(){
       Patient p1=new Patient();
       patientRepository.save(p1);
    }

    @Test
    public void testTransectionMethods() {
//        Patient patient = patientRepository.findByName("Diya Patel");
//        System.out.println(patient);

//        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1993, 3, 14));
//        for (Patient patient : patientList) {
//            System.out.println(patient);
//        }
        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(1,2,Sort.by("name")));

        for(Patient patient: patientList) {
            System.out.println(patient);
        }

//        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for(Object[] objects: bloodGroupList) {
//            System.out.println(objects[0] +" "+ objects[1]);
//        }

//        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for(BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupList) {
//            System.out.println(bloodGroupCountResponse);
//        }

//        int rowsUpdated = patientRepository.updateNameWithId("Aarav Sharma",1L);
//        System.out.println(rowsUpdated);
    }

}
