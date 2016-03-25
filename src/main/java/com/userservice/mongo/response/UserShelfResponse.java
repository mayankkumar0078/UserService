package com.userservice.mongo.response;

import com.userservice.mongo.document.UserShelfDocument;
import com.userservice.response.BaseResponse;

public class UserShelfResponse extends BaseResponse{
	
private static final long serialVersionUID = 1L;
private UserShelfDocument userShelfDocument;

public UserShelfDocument getUserShelfDocument() {
	return userShelfDocument;
}

public void setUserShelfDocument(UserShelfDocument userShelfDocument) {
	this.userShelfDocument = userShelfDocument;
}
}
