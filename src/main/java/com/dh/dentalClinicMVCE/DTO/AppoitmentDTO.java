package com.dh.dentalClinicMVCE.DTO;

import java.io.Serializable;

public class AppoitmentDTO {

    private Long id;
    private Long dentist_id;
    private Long patient_id;
    private String date;

    public AppoitmentDTO() {
    }

    public AppoitmentDTO(Long id, Long patient_id, Long dentist_id, String date) {
        this.id = id;
        this.patient_id = patient_id;
        this.dentist_id = dentist_id;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDentist_id() {
        return dentist_id;
    }

    public void setDentist_id(Long dentist_id) {
        this.dentist_id = dentist_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }
}