package com.dh.dentalClinicMVCE.Reposittory;

import com.dh.dentalClinicMVCE.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepo extends JpaRepository<Patient, Long> {

}
