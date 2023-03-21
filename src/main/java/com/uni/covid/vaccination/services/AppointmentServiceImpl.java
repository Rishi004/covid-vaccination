package com.uni.covid.vaccination.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.entities.Appointments;
import com.uni.covid.vaccination.entities.QAppointments;
import com.uni.covid.vaccination.entities.User;
import com.uni.covid.vaccination.repositories.AppointmentRepository;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;
import com.uni.covid.vaccination.util.Utils;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public void saveAppointment(AppointmentDto appointmentDto) {
		Appointments appointments = new Appointments();
		BeanUtils.copyProperties(appointmentDto, appointments);

		User hospital = new User();
		hospital.setId(appointmentDto.getHospitalId());
		appointments.setHospital(hospital);

		User user = new User();
		user.setId(appointmentDto.getUserId());
		appointments.setUser(user);

		appointmentRepository.save(appointments);
	}

	@Override
	public boolean isIdExists(Long id) {
		return appointmentRepository.existsById(id);
	}

	@Override
	public Appointments getAppointmentById(Long id) {
		return appointmentRepository.findById(id).get();
	}

	@Override
	public boolean isUserIdExists(Long id) {
		return appointmentRepository.existsByUserId(id);
	}

	@Override
	public void deleteAppointmentById(Long id) {
		appointmentRepository.deleteById(id);
	}

	@Override
	public void editAppointment(AppointmentDto appointmentDto) {
		Appointments appointments = appointmentRepository.findById(appointmentDto.getId()).get();
		BeanUtils.copyProperties(appointmentDto, appointments);

		User hospital = new User();
		hospital.setId(appointmentDto.getHospitalId());
		appointments.setHospital(hospital);

		User user = new User();
		user.setId(appointmentDto.getUserId());
		appointments.setUser(user);

		appointmentRepository.save(appointments);
	}

	@Override
	public List<Appointments> searchAppointment(String userId, String hospital, Pageable pageable,
			Pagination pagination) {

		Long totalRecords = 0L;

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(userId)) {
			double id = Double.valueOf(userId);
			booleanBuilder.and(QAppointments.appointments.user.id.eq((long) id));
		}

		if (Utils.isNotNullAndEmpty(hospital)) {
			booleanBuilder.and(QAppointments.appointments.hospital.firstName.containsIgnoreCase(hospital));
		}

		totalRecords = appointmentRepository.count(booleanBuilder);
		int totalpage = (int) Math.ceil(((double) totalRecords / (double) pagination.getPageSize()));
		pagination.setTotalRecords(totalRecords);
		pagination.setTotalPages(totalpage);
		return appointmentRepository.findAll(booleanBuilder, pageable).toList();
	}

}
