//package com.Practice.hospitalManagement;
//
//import com.Practice.hospitalManagement.entity.Insurance;
//import com.Practice.hospitalManagement.entity.Patient;
//import com.Practice.hospitalManagement.service.InsuranceService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//@SpringBootTest
//public class InsuranceTests {
//
//    @Autowired
//    private InsuranceService insuranceService;
//
//    @Test
//    public void testInsurance(){
//        Insurance insurance  = Insurance.builder()
//                .policyNumber("HDFC_1234")
//                .provider("HDFC")
//                .validUntil(LocalDate.of(2030,12,12))
//                .build();
//
//        Patient patient  = insuranceService.assignInsurancePatient(insurance,1L);
//        System.out.println(patient);
//    }
//}


package com.Practice.hospitalManagement;

import com.Practice.hospitalManagement.entity.Insurance;
import com.Practice.hospitalManagement.entity.Patient;
import com.Practice.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional; // 1. Add this import

import java.time.LocalDate;

@SpringBootTest
@Transactional // 2. Add this annotation here
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance(){
        Insurance insurance  = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient  = insuranceService.assignInsurancePatient(insurance, 1L);
        System.out.println(patient);
    }
}