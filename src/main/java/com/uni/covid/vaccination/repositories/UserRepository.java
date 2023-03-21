package com.uni.covid.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.uni.covid.vaccination.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

	boolean existsByEmailIgnoreCase(String email);

	User findByEmailIgnoreCase(String email);

	List<User> findAllByRoleNameIgnoreCase(String roleName);

	boolean existsByEmailAndIdNot(String email, Long id);

	boolean existsByFirstName(String firstName);

}
