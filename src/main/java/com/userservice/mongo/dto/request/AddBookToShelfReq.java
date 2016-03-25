package com.userservice.mongo.dto.request;

import com.userservice.mongo.domain.ShelfBook;

public class AddBookToShelfReq {
private String userId;
private String oldShelfName;
private String newShelfName;
private ShelfBook book;

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
public ShelfBook getBook() {
	return book;
}
public void setBook(ShelfBook book) {
	this.book = book;
}
}
