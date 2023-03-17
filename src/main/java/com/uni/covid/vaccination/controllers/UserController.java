package com.uni.covid.vaccination.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;
import com.uni.covid.vaccination.enums.RestApiResponseStatus;
import com.uni.covid.vaccination.responses.ContentResponse;
import com.uni.covid.vaccination.responses.ValidationFailureResponse;
import com.uni.covid.vaccination.services.UserService;
import com.uni.covid.vaccination.util.Constants;
import com.uni.covid.vaccination.util.EndPointURI;
import com.uni.covid.vaccination.util.ValidationFailureStatusCodes;

@RestController
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
		userService.saveUser(userDto);
		UserResponseDto userResponseDto = userService.convertToUserResponseDto(userDto);
		return new ResponseEntity<>(new ContentResponse<>(Constants.USER, userResponseDto, RestApiResponseStatus.OK), null,
				HttpStatus.OK);
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
}