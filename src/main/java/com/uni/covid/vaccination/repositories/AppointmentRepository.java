package com.uni.covid.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.Appointments;

@Repository
public interface AppointmentRepository
		extends JpaRepository<Appointments, Long>, QuerydslPredicateExecutor<Appointments> {

	boolean existsByUserId(Long id);

	List<Appointments> findAllByUserId(Long userId);

	@Query("SELECT v.id, v.appointmentDate, v.vaccineType, COUNT(v.vaccineType) AS Doses, v.hospital FROM Appointments v WHERE v.user.id = :id GROUP BY v.hospital.id, v.vaccineType")
	List<Object[]> findVaccineDosesByVaccineName(@Param("id") Long id);

}
