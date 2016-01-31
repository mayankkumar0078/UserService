package com.userservice.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
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

	private static final Logger log=Logger.getLogger(DatabaseUserAcountRepository.class);
	 
	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
	 
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
		save(user);

	}

	public void deleteUser(String username) throws UsernameNotFoundException{
		User user=(User)loadUserByUsername(username);
		userRepository.delete(user);

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
		final User user =(User) userRepository.findByEmail(username);
        log.info("UserService  "+user);
        if (user == null) {
        	log.error("User not found");
            throw new UsernameNotFoundException("user not found");
        }
      //  detailsChecker.check(user);
        return user;
    
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
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
	
	public void save(UserDetails user){
		userRepository.save((User)user);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

}
