package com.userservice.mongo.document;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.userservice.mongo.domain.Shelf;
import com.userservice.mongo.domain.User;

@Document(collection="user-shelf")
public class UserShelfDocument {

	@Id
	private String id;
	private List<Shelf> shelves;
	private User user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Shelf> getShelves() {
		return shelves;
	}
	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
