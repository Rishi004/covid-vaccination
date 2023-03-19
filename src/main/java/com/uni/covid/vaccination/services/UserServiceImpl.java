package com.uni.covid.vaccination.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;
import com.uni.covid.vaccination.entities.User;
import com.uni.covid.vaccination.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isUserMailExists(String email) {
		return userRepository.existsByEmailIgnoreCase(email);
	}

	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if (user.getPassword() != null)
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setStatus(true);
		userRepository.save(user);
	}

	@Override
	public boolean isPasswordCorrect(UserLoginDto userLoginDto) {
		User user = userRepository.findByEmailIgnoreCase(userLoginDto.getEmail());
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
	}

	@Override
	public UserResponseDto getUserByEmail(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);
		return userResponseDto;
	}

	@Override
	public UserResponseDto convertToUserResponseDto(UserDto userDto) {
		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(userDto, userResponseDto);
		return userResponseDto;
	}

	@Override
	public List<UserResponseDto> getUserByRole(String roleName) {
		List<User> usersList = userRepository.findAllByRoleNameIgnoreCase(roleName);
		List<UserResponseDto> userResponseDtoList = new ArrayList<>();
		for (User user : usersList) {
			UserResponseDto userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(user, userResponseDto);
			userResponseDtoList.add(userResponseDto);
		}

		return userResponseDtoList;
	}

	@Override
	public void deleteUserHospitalById(Long id) {
		userRepository.deleteById(id);
	}

}
