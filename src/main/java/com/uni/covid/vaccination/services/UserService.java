package com.uni.covid.vaccination.services;

import java.util.List;

import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;

public interface UserService {

	boolean isUserMailExists(String email);

	void saveUser(UserDto userDto);

	boolean isPasswordCorrect(UserLoginDto userLoginDto);

	UserResponseDto getUserByEmail(String email);

	UserResponseDto convertToUserResponseDto(UserDto userDto);

	List<UserResponseDto> getUserByRole(String roleName);

	void deleteUserHospitalById(Long id);

	boolean isUserIdExists(Long id);

	UserResponseDto getUserById(Long id);

	void editUser(UserDto userDto);

	List<UserResponseDto> getUserAllUsers();

	boolean isUserEmailExistsNotId(String email, Long id);

}
