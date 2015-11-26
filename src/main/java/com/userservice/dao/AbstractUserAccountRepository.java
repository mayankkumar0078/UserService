package com.userservice.dao;

import java.sql.Date;
import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.userservice.constants.AccountState;
import com.userservice.entity.User;
/**
 * 
 * @author MAYANK
 *
 */
public abstract class AbstractUserAccountRepository implements
		UserAccountRepository {
	protected abstract void setEnabledFlag(String email, boolean flag)
			throws NoSuchElementException;

	protected abstract void updateLoginAttemptsCounter(String email,
			int attempts) throws NoSuchElementException;

	public AbstractUserAccountRepository() {
		System.out.println(this.getClass().getName() + " created");
	}

	public boolean isActivated(String email) {
		UserDetails user = loadUserByUsername(email);
		return user.isEnabled();
	}

	public void decrementAttemptsLeft(String email) {
		User user = (User) loadUserByUsername(email);
		int attempts = user.getLoginAttemptsLeft();
		updateLoginAttemptsCounter(email, --attempts);
	}

	public void setAttemptsLeft(String email, int numAttemptsAllowed) {
		User user = (User) loadUserByUsername(email);
		if (user != null) {
			updateLoginAttemptsCounter(email, numAttemptsAllowed);
		}

	}

	public AccountState isAccountLocked(String email) {
		User user = null;
		try {
			user = (User) loadUserByUsername(email);
		} catch (UsernameNotFoundException unfe) {
			return AccountState.NOT_EXIST;
		}

		if (!user.isEnabled()) {
			if (user.getLoginAttemptsLeft() == 0) {
				return AccountState.LOCKED;
			} else {
				return AccountState.DEACTIVATED;
			}
		}

		return AccountState.OK;
	}

	public String getEncodedPassword(String email) {
		UserDetails user = loadUserByUsername(email);
		return user.getPassword();
	}

	public Date getPasswordLastChangeDate(String email) {
		User user = (User) loadUserByUsername(email);
		Date retVal = user.getPasswordLastChangeDate();
		return retVal;
	}
}
