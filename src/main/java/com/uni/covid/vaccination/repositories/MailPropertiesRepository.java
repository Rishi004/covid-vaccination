package com.uni.covid.vaccination.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.MailProperties;

@Repository
public interface MailPropertiesRepository extends JpaRepository<MailProperties, Long> {

	public Optional<MailProperties> findByActive(boolean isActive);

	public boolean existsByUsernameIgnoreCase(String username);

	Page<MailProperties> findAll(Pageable pageable);

	public boolean existsByActive(boolean check);
}
