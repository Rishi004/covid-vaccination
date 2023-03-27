package com.uni.covid.vaccination.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.uni.covid.vaccination.dto.ChangePasswordDto;
import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserEditDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;

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

	void editUser(UserEditDto userEditDto);

	List<UserResponseDto> getUserAllUsers();

	boolean isUserEmailExistsNotId(String email, Long id);

	boolean isFirstNameExists(String firstName);

	boolean isPasswordSame(ChangePasswordDto changePasswordDto);

	void changePassword(ChangePasswordDto changePasswordDto);

	List<UserResponseDto> searchUser(String name, String role, Pageable pageable, Pagination pagination);

}
