package com.uni.covid.vaccination.validation;

public class ValidationFailure {

	public ValidationFailure(String field, String code) {
		this.message = field;
		this.code = code;
	}

	private String message;
	private String code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
