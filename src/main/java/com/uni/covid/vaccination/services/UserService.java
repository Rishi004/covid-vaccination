package com.uni.covid.vaccination.services;

import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;

public interface UserService {

	boolean isUserMailExists(String email);

	void saveUser(UserDto userDto);

	boolean isPasswordCorrect(UserLoginDto userLoginDto);

	UserResponseDto getUserByEmail(String email);

	UserResponseDto convertToUserResponseDto(UserDto userDto);

}
