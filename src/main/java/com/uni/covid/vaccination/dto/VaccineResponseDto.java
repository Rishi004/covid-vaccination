package com.uni.covid.vaccination.dto;

import com.uni.covid.vaccination.entities.User;

import lombok.Data;

@Data
public class VaccineResponseDto {
	private Long id;
	private String vaccineName;
	private String vaccineStatus;
	private User hospital;
}
