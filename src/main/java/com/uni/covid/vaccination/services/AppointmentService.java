package com.uni.covid.vaccination.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.dto.MyVaccineDto;
import com.uni.covid.vaccination.entities.Appointments;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;

public interface AppointmentService {

	void saveAppointment(AppointmentDto appointmentDto);

	boolean isIdExists(Long id);

	Appointments getAppointmentById(Long id);

	boolean isUserIdExists(Long id);

	void deleteAppointmentById(Long id);

	void editAppointment(AppointmentDto appointmentDto);

	List<Appointments> searchAppointment(String userId, String hospital, Pageable pageable, Pagination pagination);

	void appointmentReminderMailSender();

	List<MyVaccineDto> myVaccine(Long id);

}
