package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.DTO.AppoitmentDTO;
import com.dh.dentalClinicMVCE.Exepcions.ResourceNotFoundException;
import com.dh.dentalClinicMVCE.entity.Appoitment;
import com.dh.dentalClinicMVCE.service.IAppoitmentService;
import com.dh.dentalClinicMVCE.service.IDentistService;
import com.dh.dentalClinicMVCE.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class AppoitmentControler {

    private IAppoitmentService iAppoitmentService;
    private IDentistService iDentistService;
    private IPatientService iPatientService;

    @Autowired
    public AppoitmentControler(IAppoitmentService iAppoitmentService, IDentistService iDentistService, IPatientService iPatientService) {
        this.iAppoitmentService = iAppoitmentService;
        this.iDentistService = iDentistService;
        this.iPatientService = iPatientService;
    }

    @GetMapping
    public ResponseEntity<List<AppoitmentDTO>> findAll() {
        return ResponseEntity.ok(iAppoitmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<AppoitmentDTO> save(@RequestBody AppoitmentDTO appoitmentDTO) {
        ResponseEntity<AppoitmentDTO> response;
        if (iDentistService.findById(appoitmentDTO.getDentist_id()).isPresent() && iPatientService.findById(appoitmentDTO.getPatient_id()).isPresent()) {
            return ResponseEntity.ok(iAppoitmentService.save(appoitmentDTO));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<AppoitmentDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<AppoitmentDTO> appointmentToLookFor = iAppoitmentService.findById(id);

        if(appointmentToLookFor.isPresent()) {
            return ResponseEntity.ok(appointmentToLookFor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<AppoitmentDTO> update(@RequestBody AppoitmentDTO appoitmentDTO) {
        ResponseEntity<AppoitmentDTO> response;

        if (iDentistService.findById(appoitmentDTO.getDentist_id()).isPresent()
                && iPatientService.findById(appoitmentDTO.getPatient_id()).isPresent()) {
            try {
                response = ResponseEntity.ok(iAppoitmentService.update(appoitmentDTO));
            } catch (Exception e) {
                response = ResponseEntity.notFound().build();
            }

        } else {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> response;
            iAppoitmentService.delete(id);

        return ResponseEntity.ok("se elimino el turno con este id" + id);
    }
}