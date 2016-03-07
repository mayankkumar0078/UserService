package com.userservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginDTO extends BaseDTO {
	
	@NotNull(message="User's email can't be null")
	@Pattern(regexp=".+@.+\\.[a-z]+",message="Please enter valid email Id.")
    private String email;
	
	@NotNull(message="User's password can't be null")
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
