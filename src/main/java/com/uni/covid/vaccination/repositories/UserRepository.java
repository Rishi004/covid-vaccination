package com.uni.covid.vaccination.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmailIgnoreCase(String email);

	User findByEmailIgnoreCase(String email);

}
