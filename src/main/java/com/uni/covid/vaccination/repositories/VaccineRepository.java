package com.uni.covid.vaccination.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

	boolean existsByVaccineNameAndHospitalId(String vaccineName, Long hospitalId);

}
