package com.uni.covid.vaccination.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = true)
	private String lastName;
	@Column(nullable = true)
	private String phoneNumber;
	@Column(nullable = true)
	private String gender;
	@Column(nullable = true)
	private int age;
	@Column(nullable = true)
	private String address;
	@Column(nullable = false)
	private String roleName;
	@Column(nullable = false)
	private boolean status;
}