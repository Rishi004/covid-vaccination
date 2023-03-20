package com.uni.covid.vaccination.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "vaccines")
@Data
public class Vaccine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vaccineName;
	private String vaccineStatus;
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private User hospital;
}
