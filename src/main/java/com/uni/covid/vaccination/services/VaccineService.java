package com.uni.covid.vaccination.services;

import java.util.List;

import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.Vaccine;

public interface VaccineService {

	boolean isVaccineNameExists(String vaccineName, Long hospitalId);

	void saveVaccine(VaccineDto vaccineDto);

	List<Vaccine> getAllVaccine();

}
