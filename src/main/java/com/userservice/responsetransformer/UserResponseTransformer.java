package com.userservice.responsetransformer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.exception.UserServiceException;
import com.userservice.response.UserResponse;
import com.userservice.validator.RequestBaseValidator;

@Component
public class UserResponseTransformer extends BaseResponseTransformer{ 
	
	@Autowired
	private RequestBaseValidator requestBaseValidator;
	
	public UserResponse buildExceptionResponse(UserServiceException e){
		UserResponse userResponse=new UserResponse();
		super.buildExceptionResponse(e, userResponse);
		return userResponse;
	}
	
	public UserResponse transformIntoResponse(List<User> users){
		UserResponse response = new UserResponse();
		response.setUsers(users);
		
		buildSuccessMessage(response);
		return response;
	}
	
	public void validateCreateRequest(UserDTO request) throws UserServiceException{
		requestBaseValidator.validateRequestDetails(request);
	}
	

	public void validateUpdateRequest(UserDTO request) throws UserServiceException{
		requestBaseValidator.validateRequestDetails(request);
	}

	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
