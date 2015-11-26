package com.userservice.controller.rest;

import java.util.Map;
/**
 * 
 * @author MAYANK
 * This class is for transferring user data to UI.
 *
 */

public class UserTransfer
{

	private final String name;

	private final Map<String, Boolean> roles;


	public UserTransfer(String userName, Map<String, Boolean> roles)
	{
		this.name = userName;
		this.roles = roles;
	}


	public String getName()
	{
		return this.name;
	}


	public Map<String, Boolean> getRoles()
	{
		return this.roles;
	}

}