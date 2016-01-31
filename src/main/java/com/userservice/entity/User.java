
package com.userservice.entity;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userservice.constants.StatusType;
/**
 * 
 * @author MAYANK
 *
 */

@Entity
public class User  extends BaseEntity  implements UserDetails {
   
	private static final long serialVersionUID = 1L;

	
    public User() {
		super();
	}

	@Column(nullable = false)
    @Length(max = 255)
    @JsonIgnore private String password;

    @Column(nullable = false)
    @Length(max = 255)
    private String email;
    
    @Column(nullable = false)
    @Length(max = 255)
    private String firstName;
    
    @Column(nullable = false)
    @Length(max = 255)
    private String lastName;
    
    @Column(nullable = false)
    @JsonIgnore private Date passwordLastChangeDate;
	
    @Column(nullable=false)
    @JsonIgnore private int loginAttemptsLeft;
    
 
    
    public User(String password, String email, String firstName,
			String lastName, Date passwordLastChangeDate,
			int loginAttemptsLeft) {
		super();
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passwordLastChangeDate = passwordLastChangeDate;
		this.loginAttemptsLeft = loginAttemptsLeft;
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

	public Date getPasswordLastChangeDate() {
		return passwordLastChangeDate;
	}

	public void setPasswordLastChangeDate(Date passwordLastChangeDate) {
		this.passwordLastChangeDate = passwordLastChangeDate;
	}

	public int getLoginAttemptsLeft() {
		return loginAttemptsLeft;
	}

	public void setLoginAttemptsLeft(int loginAttemptsLeft) {
		this.loginAttemptsLeft = loginAttemptsLeft;
	}

	
    
    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setEmail(String email) {
	this.email = email;
    }

   
    @Override
    public String toString() {
	return "User [email=" + email + "]";
    }

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		return email;
	}
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
	}
	
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
	}
	
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
	}
	
	@JsonIgnore
	public boolean isEnabled() {
		return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
	}
	


}
