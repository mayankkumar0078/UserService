package com.userservice.mongo.domain;

import java.util.Calendar;
import java.util.List;

import com.cd.notes.mongo.domain.Notes;

public class NotesShelf {

	private String shelfName;
	private Calendar date;
	private List<Notes> notesList;
	
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public List<Notes> getNotesList() {
		return notesList;
	}
	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}
}
