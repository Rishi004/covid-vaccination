package com.uni.covid.vaccination.services;

import com.uni.covid.vaccination.entities.Appointments;

public interface MailNotificationsServices {

	void appointmentReminderMail(Appointments appointments);
}
