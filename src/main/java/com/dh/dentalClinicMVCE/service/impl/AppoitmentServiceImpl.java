package com.dh.dentalClinicMVCE.service.impl;

import com.dh.dentalClinicMVCE.DTO.AppoitmentDTO;
import com.dh.dentalClinicMVCE.Exepcions.ResourceNotFoundException;
import com.dh.dentalClinicMVCE.entity.Appoitment;
import com.dh.dentalClinicMVCE.Reposittory.IAppoitmentRepo;
import com.dh.dentalClinicMVCE.entity.Dentist;
import com.dh.dentalClinicMVCE.entity.Patient;
import com.dh.dentalClinicMVCE.service.IAppoitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppoitmentServiceImpl implements IAppoitmentService {
    private IAppoitmentRepo iAppoitmentRepo;

    @Autowired
    public AppoitmentServiceImpl(IAppoitmentRepo iAppoitmentRepo) {
        this.iAppoitmentRepo = iAppoitmentRepo;
    }

    @Override
    public AppoitmentDTO save(AppoitmentDTO appoitmentDTO) {
        Appoitment appoitmentEntity = new Appoitment();
        Patient patientEntity = new Patient();
        patientEntity.setId(appoitmentDTO.getPatient_id());

        Dentist dentistEntity = new Dentist();
        dentistEntity.setId(appoitmentDTO.getDentist_id());

        appoitmentEntity.setPatient(patientEntity);
        appoitmentEntity.setDentist(dentistEntity);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(appoitmentDTO.getDate(), formatter);
        appoitmentEntity.setDate(date);

        Appoitment savedAppoitment = iAppoitmentRepo.save(appoitmentEntity);

        AppoitmentDTO appoitmentDTOR = new AppoitmentDTO();
        appoitmentDTOR.setId(savedAppoitment.getId());
        appoitmentDTOR.setDate(savedAppoitment.getDate().toString());
        appoitmentDTOR.setDentist_id(savedAppoitment.getDentist().getId());
        appoitmentDTOR.setPatient_id(savedAppoitment.getPatient().getId());
        return appoitmentDTOR;
    }

    @Override
    public Optional<AppoitmentDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Appoitment> appoitment = iAppoitmentRepo.findById(id);
        Optional<AppoitmentDTO> appoitmentdto = null;
        if (appoitment.isPresent()) {
            AppoitmentDTO appoitmentDTOr = new AppoitmentDTO();
            appoitmentDTOr.setId(appoitment.get().getId());
            appoitmentDTOr.setDate(appoitment.get().getDate().toString());
            appoitmentDTOr.setDentist_id(appoitment.get().getDentist().getId());
            appoitmentDTOr.setPatient_id(appoitment.get().getPatient().getId());
            appoitmentdto = Optional.of(appoitmentDTOr);
        return appoitmentdto;
        } else {
            throw new ResourceNotFoundException("no se encontro el turno con este id" + id);
        }
    }

    @Override
    public AppoitmentDTO update(AppoitmentDTO appoitmentDTO) throws Exception {

        if (iAppoitmentRepo.findById(appoitmentDTO.getId()).isPresent()){
            Optional<Appoitment> appoitmentEntity = iAppoitmentRepo.findById(appoitmentDTO.getId());

            Patient patientEntity = new Patient();
            patientEntity.setId(appoitmentDTO.getPatient_id());

            Dentist dentistEntity = new Dentist();
            dentistEntity.setId(appoitmentDTO.getDentist_id());

            appoitmentEntity.get().setPatient(patientEntity);
            appoitmentEntity.get().setDentist(dentistEntity);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(appoitmentDTO.getDate(), formatter);
            appoitmentEntity.get().setDate(date);

            iAppoitmentRepo.save(appoitmentEntity.get());

            AppoitmentDTO appoitmentDTO1 = new AppoitmentDTO();
            appoitmentDTO1.setId(appoitmentEntity.get().getId());
            appoitmentDTO1.setPatient_id(appoitmentEntity.get().getPatient().getId());
            appoitmentDTO1.setDentist_id(appoitmentEntity.get().getDentist().getId());
            appoitmentDTO1.setDate(appoitmentEntity.get().getDate().toString());

            return appoitmentDTO1;
        }else{
            throw new Exception("no se pudo");
        }
    }

    @Override
    public Optional<AppoitmentDTO> delete(Long id) throws ResourceNotFoundException {
        Optional<Appoitment> appoitmentToLook = iAppoitmentRepo.findById(id);
        Optional<AppoitmentDTO> appoitmentDTO;
        if (!appoitmentToLook.isPresent()){
            throw new ResourceNotFoundException("no se encontro  un turno id" + id);
        }
        Appoitment appoitment = appoitmentToLook.get();
        AppoitmentDTO appoitmentDTOR = new AppoitmentDTO();
        appoitmentDTOR.setId(appoitment.getId());
        appoitmentDTOR.setDentist_id(appoitment.getDentist().getId());
        appoitmentDTOR.setPatient_id(appoitment.getPatient().getId());
        appoitmentDTOR.setDate(appoitment.getDate().toString());

        appoitmentDTO = Optional.of(appoitmentDTOR);
        return appoitmentDTO;
    }

    @Override
    public List<AppoitmentDTO> findAll() {
        List<Appoitment> appoitments = iAppoitmentRepo.findAll();
        List<AppoitmentDTO> appoitmentDTOS = new ArrayList<>();

        for (Appoitment appoitment : appoitments) {
            appoitmentDTOS.add(new AppoitmentDTO(appoitment.getId(), appoitment.getPatient().getId(), appoitment.getDentist().getId(), appoitment.getDate().toString()));
        }
        return appoitmentDTOS;
    }
}