package com.dh.dentalClinicMVCE.service;

import com.dh.dentalClinicMVCE.DTO.AppoitmentDTO;
import com.dh.dentalClinicMVCE.Exepcions.ResourceNotFoundException;
import com.dh.dentalClinicMVCE.entity.Appoitment;

import java.util.List;
import java.util.Optional;

public interface IAppoitmentService {
    AppoitmentDTO save(AppoitmentDTO appoitmentDTO);

    Optional<AppoitmentDTO> findById(Long id) throws ResourceNotFoundException;

    AppoitmentDTO update(AppoitmentDTO appoitmentDTO) throws Exception;

    Optional<AppoitmentDTO> delete(Long id) throws ResourceNotFoundException;

    List<AppoitmentDTO> findAll();
}