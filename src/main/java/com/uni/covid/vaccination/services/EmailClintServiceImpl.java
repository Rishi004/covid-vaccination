package com.uni.covid.vaccination.services;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.covid.vaccination.config.EmailConfigurationProperties;
import com.uni.covid.vaccination.entities.MailProperties;
import com.uni.covid.vaccination.repositories.MailPropertiesRepository;

@Service
public class EmailClintServiceImpl implements EmailClintService {

	@Autowired
	private MailPropertiesRepository mailPropertiesRepository;

	@Transactional
	public boolean sendTextMail(String to, String subject, String body) {
		try {
			JavaMailSender mailSender = getMailSender();
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Transactional
	public boolean sendHtmlMail(List<String> emailsList, String subject, String body) {
		JavaMailSender mailSender = getMailSender();
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			String[] emails = new String[emailsList.size()];
			emailsList.toArray(emails);

			helper = new MimeMessageHelper(msg, true);
			helper.setTo(emails);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(msg);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}

	@Transactional
	public boolean sendHtmlMail(String to, String subject, String body) {
		JavaMailSender mailSender = getMailSender();
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);

			mailSender.send(msg);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}

	@Transactional
	private JavaMailSender getMailSender() {
		MailProperties mailProperties = mailPropertiesRepository.findByActive(true).get();
		EmailConfigurationProperties mailSenders = new EmailConfigurationProperties(mailProperties);
		return mailSenders.getJavaMailSender();
	}

}
