package com.userservice.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.userservice.exception.UserServiceException;

@Component
public class RequestBaseValidator {

	@Autowired
	protected Validator validator;

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public <T> void validateRequestDetails(T requestDetails)
			throws UserServiceException {
		final StringBuilder validationMessage = new StringBuilder();

		// validate request
		final Set<ConstraintViolation<T>> violations = validator
				.validate(requestDetails);

		// throw exception in case of validation failure
		if (!CollectionUtils.isEmpty(violations)) {
			InvalidInputMessageGenerator
					.preparesErrorMessageFromConstraintViolations(
							validationMessage, violations);
			throw new UserServiceException(1,
					HttpStatus.PRECONDITION_FAILED.value(),
					HttpStatus.PRECONDITION_FAILED.value(),
					validationMessage.toString());
		}
	}
}
