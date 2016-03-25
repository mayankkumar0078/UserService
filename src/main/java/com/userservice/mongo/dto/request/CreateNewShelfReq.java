package com.userservice.mongo.dto.request;

import com.userservice.mongo.domain.User;

public class CreateNewShelfReq {
private User user;
private String shelfName;

public String getShelfName() {
	return shelfName;
}
public void setShelfName(String shelfName) {
	this.shelfName = shelfName;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

}
