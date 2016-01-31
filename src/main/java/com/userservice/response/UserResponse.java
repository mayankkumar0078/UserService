package com.userservice.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.userservice.entity.User;

@JsonInclude(Include.NON_NULL)
public class UserResponse extends BaseResponse {
	private List<User> users;
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}
	

}
