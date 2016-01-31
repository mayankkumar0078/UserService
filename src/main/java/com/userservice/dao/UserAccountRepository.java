package com.userservice.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.security.provisioning.UserDetailsManager;

import com.userservice.constants.AccountState;
import com.userservice.entity.User;
/**
 * 
 * @author MAYANK
 *
 */
public interface UserAccountRepository extends UserDetailsManager{
	void setEnabled(String email);
	void setDisabled(String email);
	boolean isActivated(String email);

	AccountState isAccountLocked(String email);
	
	void decrementAttemptsLeft(String email); 
	void setAttemptsLeft(String email, int numAttemptsAllowed);

	
	void setPassword(String email, String newPassword); 
	
	String getEncodedPassword(String username);
	Date getPasswordLastChangeDate(String email);
	
	void setAuthority(String username, String authority);
	List<User> findAll(); 
	
	void save(User user);
}
