package com.uni.covid.vaccination.services;

import java.util.List;

public interface EmailClintService {

	public boolean sendTextMail(String to, String subject, String body);

	public boolean sendHtmlMail(String to, String subject, String body);

	public boolean sendHtmlMail(List<String> emailsList, String subject, String body);

}
