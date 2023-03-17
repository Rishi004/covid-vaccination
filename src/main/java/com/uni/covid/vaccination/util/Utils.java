package com.uni.covid.vaccination.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static final String FORMAT = "yyyy-MM-dd";
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static String timeFormat(Timestamp dateAndTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
		LocalDateTime instant = dateAndTime.toLocalDateTime();
		return instant.format(formatter);
	}

	public static boolean isNotNullAndEmpty(String field) {
		return field != null && !field.isEmpty();
	}

	public static boolean isNotNullAndEmptyId(Long field) {
		return field != null;
	}

	public static String getStringFromDate(Timestamp date) {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(date);
	}

	private Utils() {
	}
}
