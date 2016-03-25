package com.userservice.mongo.dto.request;

public class RenameShelfReq {

	private String userId;
	private String newShelfName;
	private String oldShelfName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNewShelfName() {
		return newShelfName;
	}
	public void setNewShelfName(String newShelfName) {
		this.newShelfName = newShelfName;
	}
	public String getOldShelfName() {
		return oldShelfName;
	}
	public void setOldShelfName(String oldShelfName) {
		this.oldShelfName = oldShelfName;
	}
}
