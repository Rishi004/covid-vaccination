package com.uni.covid.vaccination.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.uni.covid.vaccination.dto.ChangePasswordDto;
import com.uni.covid.vaccination.dto.UserDto;
import com.uni.covid.vaccination.dto.UserEditDto;
import com.uni.covid.vaccination.dto.UserLoginDto;
import com.uni.covid.vaccination.dto.UserResponseDto;
import com.uni.covid.vaccination.entities.QUser;
import com.uni.covid.vaccination.entities.User;
import com.uni.covid.vaccination.entities.Vaccine;
import com.uni.covid.vaccination.repositories.UserRepository;
import com.uni.covid.vaccination.repositories.VaccineRepository;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;
import com.uni.covid.vaccination.util.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VaccineRepository vaccineRepository;

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
		User user = userRepository.findByEmailIgnoreCase(userDto.getEmail());
		UserResponseDto userResponseDto = new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);
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

	@Override
	public boolean isUserIdExists(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public UserResponseDto getUserById(Long id) {
		UserResponseDto userResponseDto = new UserResponseDto();
		User user = userRepository.findById(id).get();
		BeanUtils.copyProperties(user, userResponseDto);
		return userResponseDto;
	}

	@Override
	public void editUser(UserEditDto userEditDto) {
		User user = userRepository.findById(userEditDto.getId()).get();
		BeanUtils.copyProperties(userEditDto, user);
		userRepository.save(user);
	}

	@Override
	public List<UserResponseDto> getUserAllUsers() {
		List<User> usersList = userRepository.findAll();
		List<UserResponseDto> userResponseDtoList = new ArrayList<>();
		for (User user : usersList) {
			if (user.getId() != 1) {
				UserResponseDto userResponseDto = new UserResponseDto();
				BeanUtils.copyProperties(user, userResponseDto);
				userResponseDtoList.add(userResponseDto);
			}
		}

		return userResponseDtoList;
	}

	@Override
	public boolean isUserEmailExistsNotId(String email, Long id) {
		return userRepository.existsByEmailAndIdNot(email, id);
	}

	@Override
	public boolean isFirstNameExists(String firstName) {
		return userRepository.existsByFirstName(firstName);
	}

	@Override
	public boolean isPasswordSame(ChangePasswordDto changePasswordDto) {
		User user = userRepository.findById(changePasswordDto.getId()).get();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword());
	}

	@Override
	public void changePassword(ChangePasswordDto changePasswordDto) {
		User user = userRepository.findById(changePasswordDto.getId()).get();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()));
		userRepository.save(user);

	}

	@Override
	public List<UserResponseDto> searchUser(String name, String role, Pageable pageable, Pagination pagination) {

		Long totalRecords = 0L;

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(role)) {
			booleanBuilder.and(QUser.user.roleName.containsIgnoreCase(role));
		}

		if (Utils.isNotNullAndEmpty(name)) {
			booleanBuilder.and(QUser.user.firstName.containsIgnoreCase(name));
		}

		totalRecords = userRepository.count(booleanBuilder);
		int totalpage = (int) Math.ceil(((double) totalRecords / (double) pagination.getPageSize()));
		pagination.setTotalRecords(totalRecords);
		pagination.setTotalPages(totalpage);

		List<User> usersList = userRepository.findAll(booleanBuilder, pageable).toList();
		List<UserResponseDto> userResponseDtoList = new ArrayList<>();
		for (User user : usersList) {
			UserResponseDto userResponseDto = new UserResponseDto();
			BeanUtils.copyProperties(user, userResponseDto);
			List<Vaccine> vaccineList = vaccineRepository.findAllByHospitalId(user.getId());
			List<Vaccine> vaccineListFinal = new ArrayList<>();
			for (Vaccine vaccine : vaccineList) {
				if (vaccine.getVaccineStatus().toUpperCase().equals("AVAILABLE")) vaccineListFinal.add(vaccine);
			}
			userResponseDto.setVaccines(vaccineListFinal);
			userResponseDtoList.add(userResponseDto);
		}
		return userResponseDtoList;
	}

}
