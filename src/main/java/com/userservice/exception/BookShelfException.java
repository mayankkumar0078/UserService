package com.userservice.exception;

public class BookShelfException extends BaseServiceException {

	public BookShelfException(int applicationCode, int code, int status,
			String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
		
	}
	public BookShelfException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
