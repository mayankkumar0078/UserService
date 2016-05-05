package com.userservice.mongo.response.transformer;

import org.springframework.stereotype.Component;

import com.userservice.mongo.response.NotesResponse;
import com.userservice.responsetransformer.BaseResponseTransformer;

@Component
public class UserNotesResponseTransformer extends BaseResponseTransformer{

	public NotesResponse transformIntoSuccessResponse(NotesResponse response){
		super.buildSuccessMessage(response);
		return response;
	}
	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
