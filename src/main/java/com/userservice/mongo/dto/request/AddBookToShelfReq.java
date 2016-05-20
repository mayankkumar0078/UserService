package com.userservice.mongo.dto.request;

import com.userservice.mongo.domain.ShelfBookInfo;

public class AddBookToShelfReq {
private String userId;
private String oldShelfName;
private String newShelfName;
private ShelfBookInfo book;

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
public ShelfBookInfo getBook() {
	return book;
}
public void setBook(ShelfBookInfo book) {
	this.book = book;
}
}
