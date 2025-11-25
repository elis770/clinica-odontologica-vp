package com.dh.dentalClinicMVCE.Reposittory;

import com.dh.dentalClinicMVCE.entity.Dentist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepo extends JpaRepository<Dentist, Long> {

    //@Query("SELECT d FROM Dentist d WHERE d.registration=?1")
    Optional<Dentist> findByRegistration(Integer registration);
}
