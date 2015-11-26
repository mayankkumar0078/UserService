package com.userservice.dao;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.userservice.constants.StatusType;
import com.userservice.entity.User;

/**
 * 
 * @author MAYANK
 *
 */

public class DatabaseUserAcountRepository extends AbstractUserAccountRepository {

	@Autowired
	private UserRepository userRepository;
	 
	public void setEnabled(String email) {
		User user=(User)loadUserByUsername(email);
		user.setStatus(StatusType.ACTIVE);
	}

	public void setDisabled(String email) {
		// TODO Auto-generated method stub

	}

	public void setPassword(String email, String newPassword) {
		User user=(User)loadUserByUsername(email);
		user.setPassword(newPassword);

	}

	public void setAuthority(String username, String authority) {
		// TODO Auto-generated method stub

	}

	public void createUser(UserDetails user) {
		userRepository.saveAndFlush((User)user);

	}

	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub

	}

	public void deleteUser(String username) {
		// TODO Auto-generated method stub

	}

	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub

	}

	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	@Override
	protected void setEnabledFlag(String email, boolean flag)
			throws NoSuchElementException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateLoginAttemptsCounter(String email, int attempts)
			throws NoSuchElementException {
		// TODO Auto-generated method stub

	}

}
