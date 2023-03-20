package com.uni.covid.vaccination.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.entities.Appointments;
import com.uni.covid.vaccination.entities.User;
import com.uni.covid.vaccination.repositories.AppointmentRepository;

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

}
