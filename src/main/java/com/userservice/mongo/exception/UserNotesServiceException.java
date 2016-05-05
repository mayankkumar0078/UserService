package com.userservice.mongo.exception;

import com.userservice.exception.BaseServiceException;

@SuppressWarnings("serial")
public class UserNotesServiceException extends BaseServiceException{

	public UserNotesServiceException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
	public UserNotesServiceException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
