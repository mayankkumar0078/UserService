package com.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;

import com.userservice.dao.UserAccountRepository;
import com.userservice.entity.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
/**
 * @author MAYANK
 */
@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    private final HashMap<String, User> userMap = new HashMap<String, User>();
    @Autowired(required=true)
	private UserAccountRepository repository;
    public final User loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user =(User) repository.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(user);
        return user;
    }

    public void addUser(User user) {
        userMap.put(user.getUsername(), user);
    }
}
