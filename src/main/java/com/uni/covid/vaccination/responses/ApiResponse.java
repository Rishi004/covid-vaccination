package com.uni.covid.vaccination.responses;

import com.uni.covid.vaccination.enums.RestApiResponseStatus;

public class ApiResponse {

	public static final ApiResponse OK = new ApiResponse(RestApiResponseStatus.OK);

	public ApiResponse(RestApiResponseStatus restApiResponseStatus) {
		this.status = restApiResponseStatus.getStatus();
		this.statusCode = restApiResponseStatus.getCode();
	}

	private String status;

	private Integer statusCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

}
