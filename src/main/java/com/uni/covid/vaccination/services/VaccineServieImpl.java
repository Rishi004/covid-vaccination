package com.uni.covid.vaccination.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.User;
import com.uni.covid.vaccination.entities.Vaccine;
import com.uni.covid.vaccination.repositories.VaccineRepository;

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

}
