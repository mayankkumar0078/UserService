package com.userservice.exception;

public class UserAuthenticationException extends UserServiceException {

	public UserAuthenticationException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
}
