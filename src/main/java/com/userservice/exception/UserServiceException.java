package com.userservice.exception;

@SuppressWarnings("serial")
public class UserServiceException extends BaseServiceException{

	public UserServiceException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
	public UserServiceException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
