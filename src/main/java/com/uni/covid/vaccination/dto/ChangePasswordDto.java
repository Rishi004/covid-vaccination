package com.uni.covid.vaccination.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
	private Long id;
	private String oldPassword;
	private String newPassword;
}
