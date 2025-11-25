package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private IPatientService patientService;
    @Test
    void findById() {
        Patient patient = new Patient();
        patient.setName("John");
        patient.setLastName("Doe");
        Patient savedPatient = patientService.save(patient);

        Optional<Patient> foundPatient = patientService.findById(savedPatient.getId());
        assertTrue(foundPatient.isPresent());
    }
}