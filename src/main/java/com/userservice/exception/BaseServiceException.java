package com.userservice.exception;

public class BaseServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	 // Code Particular to the application
    private int applicationCode;

    // Code Particular to the service
    private int code;

    private String exceptionMessage;

    private int status;

    public BaseServiceException(int applicationCode, int code,
            int status, String exceptionMessage) {
        super(exceptionMessage);
        this.applicationCode = applicationCode;
        this.code = code;
        this.setStatus(status);
        this.exceptionMessage = exceptionMessage;
    }

    public BaseServiceException(int applicationCode, int code,
            int status, String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
        this.applicationCode = applicationCode;
        this.code = code;
        this.setStatus(status);
        this.exceptionMessage = exceptionMessage;
    }

    public BaseServiceException(String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public int getApplicationCode() {
        return applicationCode;
    }

    public int getCode() {
        return code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setApplicationCode(int applicationCode) {
        this.applicationCode = applicationCode;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
