package com.uni.covid.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.Appointments;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long> {

	boolean existsByUserId(Long id);

	List<Appointments> findAllByUserId(Long userId);

}
