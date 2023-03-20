package com.uni.covid.vaccination.services;

import java.util.List;

import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.Vaccine;

public interface VaccineService {

	void saveVaccine(VaccineDto vaccineDto);

	List<Vaccine> getAllVaccine();

	boolean isVaccineIdExists(Long id);

	void deleteVaccineById(Long id);

	void editVaccine(VaccineDto vaccineDto);

}
