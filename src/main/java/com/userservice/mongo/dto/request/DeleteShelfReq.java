package com.userservice.mongo.dto.request;

public class DeleteShelfReq {

	private String userId;
	private String shelfName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
}
