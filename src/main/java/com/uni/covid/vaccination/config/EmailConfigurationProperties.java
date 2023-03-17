package com.uni.covid.vaccination.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.uni.covid.vaccination.entities.MailProperties;

@Configuration
@Service
public class EmailConfigurationProperties {

	private MailProperties mailProperties;

	public EmailConfigurationProperties() {
	}

	public EmailConfigurationProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		if (mailProperties != null) {
			mailSender.setHost(mailProperties.getHost());
			mailSender.setPort(mailProperties.getPort());
			mailSender.setUsername(mailProperties.getUsername());
			mailSender.setPassword(mailProperties.getPassword());
			mailSender.setProtocol(mailProperties.getProtocol());
		}
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.connectiontimeout", "50000");
		props.put("mail.smtp.timeout", "50000");
		props.put("mail.smtp.writetimeout", "50000");
		props.put("mail.debug", "true");

		return mailSender;
	}
}