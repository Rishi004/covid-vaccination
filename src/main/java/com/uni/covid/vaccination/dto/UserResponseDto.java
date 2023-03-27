package com.uni.covid.vaccination.dto;

import java.util.List;

import com.uni.covid.vaccination.entities.Vaccine;

import lombok.Data;

@Data
public class UserResponseDto {
	private Long id;
	private String regNumber;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String gender;
	private int age;
	private String address;
	private String roleName;
	private List<Vaccine> vaccines;
}
