package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.Exepcions.ResourceNotFoundException;
import com.dh.dentalClinicMVCE.entity.Dentist;

import java.util.List;
import java.util.Optional;

public interface IDentistService {
    Dentist save(Dentist dentist);

    Optional<Dentist> findById(Long id);

    void update(Dentist dentist);

    void delete(Long id) throws ResourceNotFoundException;

    List<Dentist> findAll();

    Optional <Dentist> findByRegistration(Integer registrations);
}
