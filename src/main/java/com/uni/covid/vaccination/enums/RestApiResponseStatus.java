package com.uni.covid.vaccination.enums;

public enum RestApiResponseStatus {

	OK("OK", 200), CREATED("CREATED", 201), VALIDATION_FAILURE("VALIDATION_FAILURE", 400), FORBIDDEN("FORBIDDEN", 403),
	ERROR("ERROR", 500);

	private String status;

	private Integer code;

	RestApiResponseStatus(String status, Integer code) {
		this.status = status;
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public Integer getCode() {
		return code;
	}
}
