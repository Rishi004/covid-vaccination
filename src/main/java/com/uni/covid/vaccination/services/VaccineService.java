package com.uni.covid.vaccination.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.uni.covid.vaccination.dto.VaccineDto;
import com.uni.covid.vaccination.entities.Vaccine;
import com.uni.covid.vaccination.responses.PaginatedContentResponse.Pagination;

public interface VaccineService {

	void saveVaccine(VaccineDto vaccineDto);

	List<Vaccine> getAllVaccine();

	boolean isVaccineIdExists(Long id);

	void deleteVaccineById(Long id);

	void editVaccine(VaccineDto vaccineDto);

	List<Vaccine> searchVaccine(String name, String hospitalId, Pageable pageable, Pagination pagination);

}
