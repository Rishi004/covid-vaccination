package com.uni.covid.vaccination.dto;

import lombok.Data;

@Data
public class VaccineDto {
	private Long id;
	private String vaccineName;
	private String vaccineType;
	private String vaccineStatus;
	private Long hospitalId;
}
