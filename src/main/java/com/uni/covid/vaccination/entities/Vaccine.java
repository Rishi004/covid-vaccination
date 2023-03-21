package com.uni.covid.vaccination.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "vaccines")
@Data
public class Vaccine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vaccineName;
	private String vaccineType;
	private String vaccineStatus;
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private User hospital;

	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
}
