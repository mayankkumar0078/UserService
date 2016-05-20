package com.cd.notes.mongo.domain;

import java.util.Calendar;
import java.util.List;

import com.userservice.mongo.document.NotesDocument;

public class NotesShelf {
	private String shelfName;
	private String description;
	private List<NotesDocument> notesList;

	private Calendar date;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public List<NotesDocument> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<NotesDocument> notesList) {
		this.notesList = notesList;
	}
}
