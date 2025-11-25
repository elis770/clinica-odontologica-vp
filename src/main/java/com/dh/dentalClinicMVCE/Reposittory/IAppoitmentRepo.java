package com.dh.dentalClinicMVCE.Reposittory;

import com.dh.dentalClinicMVCE.entity.Appoitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppoitmentRepo extends JpaRepository<Appoitment, Long> {

}
