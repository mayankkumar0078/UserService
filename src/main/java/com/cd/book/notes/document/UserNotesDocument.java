package com.cd.book.notes.document;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cd.notes.mongo.domain.NotesShelf;
import com.userservice.mongo.domain.User;

@Document(collection="user_notes")
public class UserNotesDocument {
	@Id
	private String id;
	@NotNull(message = "User can't be null")
	private User user;
	private List<NotesShelf> notesShelves;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<NotesShelf> getNotesShelves() {
		return notesShelves;
	}
	public void setNotesShelves(List<NotesShelf> notesShelves) {
		this.notesShelves = notesShelves;
	}
}
