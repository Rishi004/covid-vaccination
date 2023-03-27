package com.uni.covid.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long>, QuerydslPredicateExecutor<Vaccine> {

	List<Vaccine> findAllByHospitalId(Long id);

}
