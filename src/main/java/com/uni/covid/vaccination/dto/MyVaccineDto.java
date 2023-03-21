package com.uni.covid.vaccination.dto;

import java.sql.Date;

import com.uni.covid.vaccination.entities.User;

import lombok.Data;

@Data
public class MyVaccineDto {
	private Long appointmentId;
	private Date vaccineDate;
	private String vaccineType;
	private User hospital;
	private long doses;
}
