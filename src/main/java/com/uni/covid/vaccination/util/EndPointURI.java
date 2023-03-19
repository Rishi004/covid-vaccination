package com.uni.covid.vaccination.util;

public class EndPointURI {

	private static final String BASE_API_PATH = "/api/v1/";
	private static final String SLASH = "/";
	private static final String ID = "{id}";
	private static final String EMAIL = "{email}";

	// user
	public static final String USER = BASE_API_PATH + "user";
	public static final String USER_BY_ID = USER + SLASH + ID;
	public static final String USER_REGISTER = USER + SLASH + "register";
	public static final String CHANGE_PASSWORD = BASE_API_PATH + "changePassword";
	public static final String USER_ADD_EMAIL = USER + SLASH + "mail";
	public static final String VIEW_USER_HOSPITAL = USER + SLASH + "{roleName}";
}
