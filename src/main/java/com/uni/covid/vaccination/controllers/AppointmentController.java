package com.uni.covid.vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uni.covid.vaccination.dto.AppointmentDto;
import com.uni.covid.vaccination.enums.RestApiResponseStatus;
import com.uni.covid.vaccination.responses.BasicResponse;
import com.uni.covid.vaccination.responses.ContentResponse;
import com.uni.covid.vaccination.responses.ValidationFailureResponse;
import com.uni.covid.vaccination.services.AppointmentService;
import com.uni.covid.vaccination.util.Constants;
import com.uni.covid.vaccination.util.EndPointURI;
import com.uni.covid.vaccination.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;

	@PostMapping(value = EndPointURI.APPOINTMENT)
	public ResponseEntity<Object> addAppointment(@RequestBody AppointmentDto appointmentDto) {
		appointmentService.saveAppointment(appointmentDto);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.APPOINTMENT),
				HttpStatus.OK);

	}

	@GetMapping(value = EndPointURI.APPOINTMENT_BY_ID)
	public ResponseEntity<Object> viewAppointmentById(@PathVariable Long id) {
		if (appointmentService.isIdExists(id)) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.APPOINTMENT_ID_NOT_EXISTS,
					validationFailureStatusCodes.getAppointmentIdNotExist()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ContentResponse<>(Constants.APPOINTMENT,
				appointmentService.getAppointmentById(id), RestApiResponseStatus.OK), null, HttpStatus.OK);
	}

}
