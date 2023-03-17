package com.uni.covid.vaccination.responses;

import java.util.Collections;
import java.util.List;

import com.uni.covid.vaccination.enums.RestApiResponseStatus;
import com.uni.covid.vaccination.validation.ValidationFailure;

public class ValidationFailureResponse extends ApiResponse {
	public ValidationFailureResponse(List<ValidationFailure> validationErrors) {
		super(RestApiResponseStatus.VALIDATION_FAILURE);
		this.validationFailures = validationErrors;
	}

	public ValidationFailureResponse(String field, String code) {
		super(RestApiResponseStatus.VALIDATION_FAILURE);
		ValidationFailure validationFailure = new ValidationFailure(field, code);
		this.validationFailures = Collections.singletonList(validationFailure);
	}

	private List<ValidationFailure> validationFailures;

	public List<ValidationFailure> getValidationFailures() {
		return validationFailures;
	}

	public void setValidationFailures(List<ValidationFailure> validationFailures) {
		this.validationFailures = validationFailures;
	}

}
