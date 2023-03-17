package com.uni.covid.vaccination.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mail_properties")
@Getter
@Setter
public class MailProperties extends DateAudit {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String host;
  private int port;
  private String username;
  private String password;
  private String protocol;
  private boolean active;

}
