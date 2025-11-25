package com.dh.dentalClinicMVCE.Controler;

import com.dh.dentalClinicMVCE.Exepcions.ResourceNotFoundException;
import com.dh.dentalClinicMVCE.service.IPatientService;
import com.dh.dentalClinicMVCE.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PatientControler {

    private IPatientService iPatientService;

    @Autowired
    public PatientControler(IPatientService iPatientService) {
        this.iPatientService = iPatientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        Optional<Patient> patient =  iPatientService.findById(id);
        if (!patient.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient.get());
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        return ResponseEntity.ok(iPatientService.save(patient));
    }

    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody Patient patient) {
        Optional<Patient> patientToLookFor = iPatientService.findById(patient.getId());
        if (patientToLookFor.isPresent()){
            return ResponseEntity.ok(iPatientService.save(patient));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        return ResponseEntity.ok(iPatientService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        iPatientService.delete(id);
        return ResponseEntity.ok("se elimino el paciente con id" + id);
    }
}