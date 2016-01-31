package com.userservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Mayank
 * 
 */  
public class UserDTO extends BaseDTO{

	@NotNull(message="User First name can't be null")
    private String firstName;
	
	@NotNull(message="User's last name can't be null")
    private String lastName;
	
	@NotNull(message="User's email can't be null")
	@Pattern(regexp=".+@.+\\.[a-z]+",message="Please enter valid email Id.")
    private String email;
	
	@NotNull(message="User's password can't be null")
	@Size(message="Password's Size can't be less than five characters" ,min=5)
    private String password;
	
	@NotNull(message="Confirm password field can't be null")
    private String confirmPassword;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
    

}
