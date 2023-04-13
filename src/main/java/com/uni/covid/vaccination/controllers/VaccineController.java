package com.uni.covid.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.Vaccine;
import com.uni.covid.vaccination.enums.RestApiResponseStatus;
import com.uni.covid.vaccination.responses.BasicResponse;
import com.uni.covid.vaccination.responses.ContentResponse;
import com.uni.covid.vaccination.responses.PaginatedContentResponse;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;
import com.uni.covid.vaccination.responses.ValidationFailureResponse;
import com.uni.covid.vaccination.services.UserService;
import com.uni.covid.vaccination.services.VaccineService;
import com.uni.covid.vaccination.util.Constants;
import com.uni.covid.vaccination.util.EndPointURI;
import com.uni.covid.vaccination.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
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

	@DeleteMapping(value = EndPointURI.VACCINE_BY_ID)
	public ResponseEntity<Object> deleteVaccineById(@PathVariable Long id) {
		if (!vaccineService.isVaccineIdExists(id)) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.VACCINE_ID_NOT_EXISTS,
					validationFailureStatusCodes.getVaccineIdNotExist()), HttpStatus.BAD_REQUEST);
		}
		try {
			vaccineService.deleteVaccineById(id);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.VACCINE_DELETED_SUCCESS), HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.VACCINE_DEPEND,
					validationFailureStatusCodes.getVaccineIdDepended()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = EndPointURI.VACCINE)
	public ResponseEntity<Object> editUser(@RequestBody VaccineDto vaccineDto) {
		if (!vaccineService.isVaccineIdExists(vaccineDto.getId())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.VACCINE_ID_NOT_EXISTS,
					validationFailureStatusCodes.getVaccineIdNotExist()), HttpStatus.BAD_REQUEST);
		}
		vaccineService.editVaccine(vaccineDto);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.VACCINE_UPDATED_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.VACCINE_SEARCH)
	public ResponseEntity<Object> searchVaccineByName(@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size, @RequestParam(name = "name") String name, @RequestParam(name = "hospitalId") String hospitalId) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
		Pagination pagination = new Pagination(page, size, 0, 0L);
		List<Vaccine> vaccineList = vaccineService.searchVaccine(name, hospitalId, pageable, pagination);
		return new ResponseEntity<>(
				new PaginatedContentResponse<>(Constants.VACCINE, vaccineList, RestApiResponseStatus.OK, pagination),
				HttpStatus.OK);

	}
}
