package com.uni.covid.vaccination.services;

import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.entities.Appointments;

public interface AppointmentService {

	void saveAppointment(AppointmentDto appointmentDto);

	boolean isIdExists(Long id);

	Appointments getAppointmentById(Long id);

}