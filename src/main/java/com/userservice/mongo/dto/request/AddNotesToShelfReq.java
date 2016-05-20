package com.userservice.mongo.dto.request;

import com.userservice.mongo.document.NotesDocument;

public class AddNotesToShelfReq {

	private String userId;
	private String oldShelfName;
	private String newShelfName;
	private NotesDocument notes;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOldShelfName() {
		return oldShelfName;
	}
	public void setOldShelfName(String oldShelfName) {
		this.oldShelfName = oldShelfName;
	}
	public String getNewShelfName() {
		return newShelfName;
	}
	public void setNewShelfName(String newShelfName) {
		this.newShelfName = newShelfName;
	}
	public NotesDocument getNotes() {
		return notes;
	}
	public void setNotes(NotesDocument notes) {
		this.notes = notes;
	}
}
