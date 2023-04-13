package com.uni.covid.vaccination.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.QAppointments;
import com.uni.covid.vaccination.entities.QVaccine;
import com.uni.covid.vaccination.entities.User;
import com.uni.covid.vaccination.entities.Vaccine;
import com.uni.covid.vaccination.repositories.VaccineRepository;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;
import com.uni.covid.vaccination.util.Utils;

@Service
public class VaccineServieImpl implements VaccineService {

	@Autowired
	private VaccineRepository vaccineRepository;

	@Override
	public void saveVaccine(VaccineDto vaccineDto) {
		User user = new User();
		Vaccine vaccine = new Vaccine();
		BeanUtils.copyProperties(vaccineDto, vaccine);
		user.setId(vaccineDto.getHospitalId());
		vaccine.setHospital(user);
		vaccineRepository.save(vaccine);
	}

	@Override
	public List<Vaccine> getAllVaccine() {
		return vaccineRepository.findAll();
	}

	@Override
	public boolean isVaccineIdExists(Long id) {
		return vaccineRepository.existsById(id);
	}

	@Override
	public void deleteVaccineById(Long id) {
		vaccineRepository.deleteById(id);
	}

	@Override
	public void editVaccine(VaccineDto vaccineDto) {
		User hospital = new User();
		Vaccine vaccine = vaccineRepository.findById(vaccineDto.getId()).get();
		BeanUtils.copyProperties(vaccineDto, vaccine);
		hospital.setId(vaccineDto.getHospitalId());
		vaccine.setHospital(hospital);
		vaccineRepository.save(vaccine);

	}

	@Override
	public List<Vaccine> searchVaccine(String name, String hospitalId, Pageable pageable, Pagination pagination) {
		Long totalRecords = 0L;

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		if (Utils.isNotNullAndEmpty(hospitalId)) {
			double id = Double.valueOf(hospitalId);
			booleanBuilder.and(QVaccine.vaccine.hospital.id.eq((long) id));
		}

		if (Utils.isNotNullAndEmpty(name)) {
			booleanBuilder.and(QVaccine.vaccine.vaccineName.containsIgnoreCase(name));
		}

		totalRecords = vaccineRepository.count(booleanBuilder);
		int totalpage = (int) Math.ceil(((double) totalRecords / (double) pagination.getPageSize()));
		pagination.setTotalRecords(totalRecords);
		pagination.setTotalPages(totalpage);
		return vaccineRepository.findAll(booleanBuilder, pageable).toList();
	}

}
