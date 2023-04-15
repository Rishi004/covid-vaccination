package com.uni.covid.vaccination.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.dto.MyVaccineDto;
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

	@Autowired
	private MailNotificationsServices mailNotificationsServices;

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
		appointments.setStatus(appointmentDto.getStatus());

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
		appointments.setStatus(appointmentDto.getStatus());

		appointmentRepository.save(appointments);
	}

	@Override
	public List<Appointments> searchAppointment(String userId, String hospital, Pageable pageable,
			Pagination pagination) {

		Long totalRecords = 0L;

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(userId)) {
		    double id = Double.valueOf(userId);
		    booleanBuilder.and(
		        QAppointments.appointments.user.id.eq((long) id)
		        .or(QAppointments.appointments.hospital.id.eq((long) id))
		    );
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

	@Override
	@Scheduled(cron = "0 0 12 * * ?")
	public void appointmentReminderMailSender() {
		List<Appointments> appointmentsList = appointmentRepository.findAll();
		Date currentDate = new Date();

		for (Appointments appointments : appointmentsList) {
			long differenceInMillis = appointments.getAppointmentDate().getTime() - currentDate.getTime();
			long differentInDays = (int) Math.floor(differenceInMillis / (24 * 60 * 60 * 1000));
			if (differentInDays <= 1) {
				mailNotificationsServices.appointmentReminderMail(appointments);
			}
		}
	}

	@Override
	public List<MyVaccineDto> myVaccine(Long id) {
		List<MyVaccineDto> myVaccineDtoList = new ArrayList<>();
		List<Object[]> resultList = appointmentRepository.findVaccineDosesByVaccineName(id);
		for (int i = 0; i < resultList.size(); i++) {
			MyVaccineDto myVaccineDto = new MyVaccineDto();

			myVaccineDto.setAppointmentId((Long) resultList.get(i)[0]);
			myVaccineDto.setVaccineDate((java.sql.Date) resultList.get(i)[1]);
			myVaccineDto.setVaccineType((String) resultList.get(i)[2]);
			myVaccineDto.setDoses((long) resultList.get(i)[3]);
			myVaccineDto.setHospital((User) resultList.get(i)[4]);

			myVaccineDtoList.add(myVaccineDto);
		}
		return myVaccineDtoList;
	}

}
