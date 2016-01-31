package com.userservice.responsetransformer;

import org.springframework.http.HttpStatus;

import com.userservice.constants.UserAccountConstants;
import com.userservice.exception.BaseServiceException;
import com.userservice.exception.UserServiceException;
import com.userservice.response.BaseResponse;

public abstract class BaseResponseTransformer {
	public BaseResponse	buildExceptionResponse(BaseServiceException e,BaseResponse baseResponse){
		baseResponse.setApplicationCode(UserAccountConstants.APPLICATION_CODE_USER_SERVICE);
		baseResponse.setCode(e.getCode());
		baseResponse.setStatus(e.getStatus());
		if(e.getCause() != null){
			baseResponse.setDeveloperMessage(e.getCause().getLocalizedMessage());
		}
		baseResponse.setMessage(e.getMessage());
		return baseResponse;
	}
	
	public void buildSuccessMessage(BaseResponse response){
		response.setCode(HttpStatus.OK.value());
		response.setApplicationCode(UserAccountConstants.APPLICATION_CODE_USER_SERVICE);
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Success");
	}
	
	protected abstract int getApplicationCode();
}
