package com.uni.covid.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;
import com.uni.covid.vaccination.enums.RestApiResponseStatus;
import com.uni.covid.vaccination.responses.BasicResponse;
import com.uni.covid.vaccination.responses.ContentResponse;
import com.uni.covid.vaccination.responses.ValidationFailureResponse;
import com.uni.covid.vaccination.services.UserService;
import com.uni.covid.vaccination.util.Constants;
import com.uni.covid.vaccination.util.EndPointURI;
import com.uni.covid.vaccination.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;

	@PostMapping(value = EndPointURI.USER_REGISTER)
	public ResponseEntity<Object> signUpUser(@RequestBody UserDto userDto) {
		if (userService.isUserMailExists(userDto.getEmail())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_MAIL_ALREADY_EXISTS,
					validationFailureStatusCodes.getUserMailAlreadyExists()), HttpStatus.BAD_REQUEST);
		}
		if (userService.isFirstNameExists(userDto.getFirstName())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.NAME_ALREADY_EXISTS,
					validationFailureStatusCodes.getNameAlreadyExist()), HttpStatus.BAD_REQUEST);
		}
		userService.saveUser(userDto);
		UserResponseDto userResponseDto = userService.convertToUserResponseDto(userDto);
		return new ResponseEntity<>(new ContentResponse<>(Constants.USER, userResponseDto, RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@PostMapping(value = EndPointURI.USER)
	public ResponseEntity<Object> logInUser(@RequestBody UserLoginDto userLoginDto) {
		if (!userService.isUserMailExists(userLoginDto.getEmail())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_MAIL_NOT_EXISTS,
					validationFailureStatusCodes.getUserMailNotExists()), HttpStatus.BAD_REQUEST);
		}

		if (!userService.isPasswordCorrect(userLoginDto)) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_WRONG_PASSWORD,
					validationFailureStatusCodes.getUserWrongPassword()), HttpStatus.BAD_REQUEST);
		}

		UserResponseDto userResponseDto = userService.getUserByEmail(userLoginDto.getEmail());
		return new ResponseEntity<>(new ContentResponse<>(Constants.USER, userResponseDto, RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.USER)
	public ResponseEntity<Object> viewAllHospitalsAndUsers() {
		List<UserResponseDto> userResponseDtoList = userService.getUserAllUsers();
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.USER, userResponseDtoList, RestApiResponseStatus.OK), null,
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.USER_BY_ROLE)
	public ResponseEntity<Object> viewHospitalsOrUsers(@PathVariable String roleName) {
		List<UserResponseDto> userResponseDtoList = userService.getUserByRole(roleName);
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.USER, userResponseDtoList, RestApiResponseStatus.OK), null,
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.USER_BY_ID)
	public ResponseEntity<Object> viewHospitalsOrUsersById(@PathVariable Long id) {
		if (!userService.isUserIdExists(id)) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID_NOT_EXISTS,
					validationFailureStatusCodes.getUserIdNotExists()), HttpStatus.BAD_REQUEST);
		}
		UserResponseDto userResponseDto = userService.getUserById(id);
		return new ResponseEntity<>(new ContentResponse<>(Constants.USER, userResponseDto, RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.USER_BY_EMAIL)
	public ResponseEntity<Object> viewHospitalsOrUsersByEmail(@PathVariable String email) {
		if (!userService.isUserMailExists(email)) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_MAIL_NOT_EXISTS,
					validationFailureStatusCodes.getUserIdNotExists()), HttpStatus.BAD_REQUEST);
		}
		UserResponseDto userResponseDto = userService.getUserByEmail(email);
		return new ResponseEntity<>(new ContentResponse<>(Constants.USER, userResponseDto, RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@DeleteMapping(value = EndPointURI.USER_BY_ID)
	public ResponseEntity<Object> deleteUserHospitalById(@PathVariable Long id) {
		if (!userService.isUserIdExists(id)) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID_NOT_EXISTS,
					validationFailureStatusCodes.getUserIdNotExists()), HttpStatus.BAD_REQUEST);
		}
		try {
			userService.deleteUserHospitalById(id);
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.USER_DELETED_SUCCESS),
					HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_DEPEND,
					validationFailureStatusCodes.getUserIdDepended()), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = EndPointURI.USER)
	public ResponseEntity<Object> editUser(@RequestBody UserDto userDto) {
		if (!userService.isUserIdExists(userDto.getId())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID_NOT_EXISTS,
					validationFailureStatusCodes.getUserIdNotExists()), HttpStatus.BAD_REQUEST);
		}
		if (userService.isUserEmailExistsNotId(userDto.getEmail(), userDto.getId())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_MAIL_ALREADY_EXISTS,
					validationFailureStatusCodes.getUserIdNotExists()), HttpStatus.BAD_REQUEST);
		}
		userService.editUser(userDto);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.USER_UPDATED_SUCCESS),
				HttpStatus.OK);
	}

}
