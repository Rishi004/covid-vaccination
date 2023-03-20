package com.uni.covid.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.Vaccine;
import com.uni.covid.vaccination.enums.RestApiResponseStatus;
import com.uni.covid.vaccination.responses.BasicResponse;
import com.uni.covid.vaccination.responses.ContentResponse;
import com.uni.covid.vaccination.responses.ValidationFailureResponse;
import com.uni.covid.vaccination.services.UserService;
import com.uni.covid.vaccination.services.VaccineService;
import com.uni.covid.vaccination.util.Constants;
import com.uni.covid.vaccination.util.EndPointURI;
import com.uni.covid.vaccination.util.ValidationFailureStatusCodes;

@RestController
public class VaccineController {

	@Autowired
	private VaccineService vaccineService;

	@Autowired
	private UserService userService;

	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;

	@PostMapping(value = EndPointURI.VACCINE)
	public ResponseEntity<Object> addVaccine(@RequestBody VaccineDto vaccineDto) {
		if (!userService.isUserIdExists(vaccineDto.getHospitalId())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID_NOT_EXISTS,
					validationFailureStatusCodes.getUserIdNotExists()), HttpStatus.BAD_REQUEST);
		}
		if (vaccineService.isVaccineNameExists(vaccineDto.getVaccineName(), vaccineDto.getHospitalId())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.VACCINE_NAME_ALREADY_EXISTS,
					validationFailureStatusCodes.getVaccineNameAlreadyExist()), HttpStatus.BAD_REQUEST);
		}
		vaccineService.saveVaccine(vaccineDto);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_VACCINE_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.VACCINE)
	public ResponseEntity<Object> getAllVaccine() {
		List<Vaccine> vaccineList = vaccineService.getAllVaccine();
		return new ResponseEntity<>(new ContentResponse<>(Constants.VACCINE, vaccineList, RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}
}
