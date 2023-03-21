package com.uni.covid.vaccination.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@PropertySource("classpath:ValidationMessages.properties")
public class ValidationFailureStatusCodes {

	// user
	@Value("${validation.user.userMailAlreadyExist}")
	private String userMailAlreadyExists;
	@Value("${validation.user.userMailNotExist}")
	private String userMailNotExists;
	@Value("${validation.user.depended}")
	private String userIdDepended;
	@Value("${validation.user.wrongPassword}")
	private String userWrongPassword;
	@Value("${validation.user.userIdNotExist}")
	private String userIdNotExists;
	@Value("${validation.user.nameAlreadyExist}")
	private String nameAlreadyExist;

	// vaccine
	@Value("${validation.vaccine.nameAlreadyExist}")
	private String vaccineNameAlreadyExist;
	@Value("${validation.vaccine.vaccineIdNotExist}")
	private String vaccineIdNotExist;
	@Value("${validation.vaccine.depended}")
	private String vaccineIdDepended;

	// appointment
	@Value("${validation.appointment.appointmentIdNotExist}")
	private String appointmentIdNotExist;
	@Value("${validation.appointment.appointmentUserIdNotExist}")
	private String appointmentUserIdNotExist;
	@Value("${validation.appointment.depended}")
	private String appointmentIdDepended;
}
