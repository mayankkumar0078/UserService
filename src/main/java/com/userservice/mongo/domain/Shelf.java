package com.userservice.mongo.domain;

import java.util.Calendar;
import java.util.List;

import com.userservice.mongo.document.NotesDocument;

public class Shelf {

	private String shelfName;
	private Calendar date;
	//a shelf can have books, notes, uploaded notes
	private List<ShelfBookInfo> books;
	private List<NotesDocument> notesList;
	private List<NotesDocument> uploadedNotes;
	
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
	public List<ShelfBookInfo> getBooks() {
		return books;
	}
	public void setBooks(List<ShelfBookInfo> books) {
		this.books = books;
	}
	
	@Override
	public boolean equals(Object o){
		 if (o == this) {
	            return true;
	        }
	        if (!(o instanceof Shelf)) {
	            return false;
	        }
	        Shelf c = (Shelf) o;
	        return shelfName.equals(c.getShelfName());
	}
	public List<NotesDocument> getUploadedNotes() {
		return uploadedNotes;
	}
	public void setUploadedNotes(List<NotesDocument> uploadedNotes) {
		this.uploadedNotes = uploadedNotes;
	}
	public List<NotesDocument> getNotesList() {
		return notesList;
	}
	public void setNotesList(List<NotesDocument> notesList) {
		this.notesList = notesList;
	}
}
