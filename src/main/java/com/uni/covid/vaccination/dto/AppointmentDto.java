package com.uni.covid.vaccination.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class AppointmentDto {
	private Long id;
	private Long hospitalId;
	private Long userId;
	private String vaccinationType;
	private Date appointmentDate;
	private Time appointmentTime;
}
