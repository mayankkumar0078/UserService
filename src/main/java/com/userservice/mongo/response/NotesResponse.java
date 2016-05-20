package com.userservice.mongo.response;

import java.util.List;

import com.userservice.mongo.document.NotesDocument;
import com.userservice.response.BaseResponse;

public class NotesResponse extends BaseResponse{

	private static final long serialVersionUID = 1L;
	private NotesDocument notes;
	private List<NotesDocument> notesList;
	private long count;

	public NotesDocument getNotes() {
		return notes;
	}

	public void setNotes(NotesDocument notes) {
		this.notes = notes;
	}

	public List<NotesDocument> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<NotesDocument> notesList) {
		this.notesList = notesList;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
