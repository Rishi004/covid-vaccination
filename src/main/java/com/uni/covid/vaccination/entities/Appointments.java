package com.uni.covid.vaccination.entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "appointments")
@Data
public class Appointments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private User hospital;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String vaccineType;
	private Date appointmentDate;
	private Time appointmentTime;
}
