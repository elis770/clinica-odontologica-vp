package com.dh.dentalClinicMVCE.service.impl;

import com.dh.dentalClinicMVCE.Exepcions.ResourceNotFoundException;
import com.dh.dentalClinicMVCE.entity.Patient;
import com.dh.dentalClinicMVCE.Reposittory.IPatientRepo;
import com.dh.dentalClinicMVCE.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientService {

    private IPatientRepo iPatientRepo;

    @Autowired
    public PatientServiceImpl(IPatientRepo iPatientRepo) {
        this.iPatientRepo = iPatientRepo;
    }

    @Override
    public Patient save(Patient patient) {
        return iPatientRepo.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return iPatientRepo.findById(id);
    }

    @Override
    public void update(Patient patient) {
        iPatientRepo.save(patient);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = findById(id);
        if (!patientOptional.isPresent()){
            throw new ResourceNotFoundException("no se pudo eliminar el paciente" + id);
        }
        iPatientRepo.deleteById(id);
    }

    @Override
    public List<Patient> findAll() {
        return iPatientRepo.findAll();
    }
}