package com.dh.dentalClinicMVCE.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dentist_id")
    private Long id;
    @Column(name = "registration")
    private Integer registration;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;

    @OneToMany(mappedBy = "dentist")
    private Set<Appoitment> appoitments = new HashSet<>();

    public Dentist(){
    }

    public Set<Appoitment> getAppoitments() {
        return appoitments;
    }

    public void setAppoitments(Set<Appoitment> appoitments) {
        this.appoitments = appoitments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }
}