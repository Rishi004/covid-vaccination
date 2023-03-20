package com.uni.covid.vaccination.services;

import java.util.List;

import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.entities.Appointments;

public interface AppointmentService {

	void saveAppointment(AppointmentDto appointmentDto);

	boolean isIdExists(Long id);

	Appointments getAppointmentById(Long id);

	boolean isUserIdExists(Long id);

	List<Appointments> getAppointmentByUserId(Long userId);

	void deleteAppointmentById(Long id);

	void editAppointment(AppointmentDto appointmentDto);

}
