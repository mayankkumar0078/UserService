package com.userservice.mongo.exception;

import com.userservice.exception.BaseServiceException;

@SuppressWarnings("serial")
public class UserShelfServiceException extends BaseServiceException{

	public UserShelfServiceException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
	public UserShelfServiceException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
