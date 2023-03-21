package com.uni.covid.vaccination.services;

import com.uni.covid.vaccination.entities.Appointments;
import com.uni.covid.vaccination.entities.User;

public interface MailNotificationsServices {

	void appointmentReminderMail(Appointments appointments);
}
