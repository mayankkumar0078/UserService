package com.userservice.mongo.response.transformer;

import org.springframework.stereotype.Component;

import com.userservice.mongo.response.UserShelfResponse;
import com.userservice.response.BaseResponse;
import com.userservice.responsetransformer.BaseResponseTransformer;

@Component
public class UserShelfResponseTransformer extends BaseResponseTransformer{

	public BaseResponse transformIntoSuccessResponse(BaseResponse response){
		super.buildSuccessMessage(response);
		return response;
	}
	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
