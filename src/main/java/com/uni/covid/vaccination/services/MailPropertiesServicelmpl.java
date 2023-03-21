package com.uni.covid.vaccination.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uni.covid.vaccination.entities.Appointments;

@Service
public class MailPropertiesServicelmpl implements MailNotificationsServices {

	@Autowired
	private EmailClintService emailClintService;

	@Override
	public void appointmentReminderMail(Appointments appointments) {
		emailClintService.sendHtmlMail(appointments.getUser().getEmail(), "APPOINTMENT REMINDER", "<!DOCTYPE html>\r\n"
				+ "<html>\r\n" + "    <head>\r\n" + "        <meta charset=\"UTF-8\" />\r\n"
				+ "        <title>Appointment Reminder</title>\r\n" + "        <style>\r\n"
				+ "            /* Your custom styles go here */\r\n" + "            body {\r\n"
				+ "                font-family: Arial, sans-serif;\r\n"
				+ "                background-color: #f1f1f1;\r\n" + "                margin: 0;\r\n"
				+ "                padding: 0;\r\n" + "            }\r\n" + "            .container {\r\n"
				+ "                max-width: 600px;\r\n" + "                margin: 0 auto;\r\n"
				+ "                background-color: #fff;\r\n" + "                padding: 20px;\r\n"
				+ "                border-radius: 5px;\r\n"
				+ "                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n" + "            }\r\n"
				+ "            h1 {\r\n" + "                font-size: 30px;\r\n"
				+ "                text-align: center;\r\n" + "                color: #444;\r\n"
				+ "                margin-bottom: 20px;\r\n" + "            }\r\n" + "            p {\r\n"
				+ "                font-size: 16px;\r\n" + "                line-height: 1.5;\r\n"
				+ "                color: #666;\r\n" + "                margin-bottom: 15px;\r\n" + "            }\r\n"
				+ "            .cta {\r\n" + "                display: block;\r\n"
				+ "                text-align: center;\r\n" + "                margin-top: 20px;\r\n"
				+ "            }\r\n" + "            .cta a {\r\n" + "                background-color: #5bc0de;\r\n"
				+ "                color: #fff;\r\n" + "                text-decoration: none;\r\n"
				+ "                padding: 10px 20px;\r\n" + "                border-radius: 5px;\r\n"
				+ "                transition: background-color 0.2s ease-in-out;\r\n" + "            }\r\n"
				+ "            .cta a:hover {\r\n" + "                background-color: #2e9cca;\r\n"
				+ "            }\r\n" + "        </style>\r\n" + "    </head>\r\n" + "    <body>\r\n"
				+ "        <div class=\"container\">\r\n" + "            <h1>Appointment Reminder</h1>\r\n"
				+ "            <p>Dear " + appointments.getUser().getFirstName() + ",</p>\r\n" + "            <p>\r\n"
				+ "                This is just a friendly reminder that you have an appointment\r\n"
				+ "                with us tomorrow, " + appointments.getAppointmentDate()
				+ ". We look forward to seeing\r\n" + "                you!\r\n" + "            </p>\r\n"
				+ "            <p>\r\n" + "                If you need to reschedule, please let us know as soon as\r\n"
				+ "                possible.\r\n" + "            </p>\r\n" + "            <p>Thank you!</p>\r\n"
				+ "            <div class=\"cta\">\r\n"
				+ "                <a href=\"http://localhost:5173/\">Reschedule Appointment</a>\r\n"
				+ "            </div>\r\n" + "        </div>\r\n" + "    </body>\r\n" + "</html>\r\n" + "");
	}
}
