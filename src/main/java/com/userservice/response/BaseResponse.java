package com.userservice.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {
	private int applicationCode;

	private int code;

	private Object data;

	private String developerMessage;

	private String message;

	private String moreInfoURL;

	private int status;

	public int getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(int applicationCode) {
		this.applicationCode = applicationCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMoreInfoURL() {
		return moreInfoURL;
	}

	public void setMoreInfoURL(String moreInfoURL) {
		this.moreInfoURL = moreInfoURL;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
