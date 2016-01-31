package com.userservice.responsetransformer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.userservice.dto.BookShelfDTO;
import com.userservice.entity.BookShelf;
import com.userservice.exception.BookShelfException;
import com.userservice.exception.UserServiceException;
import com.userservice.response.BookShelfResponse;
import com.userservice.validator.RequestBaseValidator;

@Component
public class BookShelfResponseTransformer extends BaseResponseTransformer{ 
	
	@Autowired
	private RequestBaseValidator requestBaseValidator;
	
	public BookShelfResponse buildExceptionResponse(BookShelfException e){
		BookShelfResponse bookShelfResponse=new BookShelfResponse();
		super.buildExceptionResponse(e, bookShelfResponse);
		return bookShelfResponse;
	}
	
	public BookShelfResponse transformIntoResponse(List<BookShelf> bookShelves){
		BookShelfResponse response = new BookShelfResponse();
		response.setBookShelves(bookShelves);
		
		buildSuccessMessage(response);
		return response;
	}
	
	public void validateCreateRequest(BookShelfDTO request) throws UserServiceException{
		requestBaseValidator.validateRequestDetails(request);
	}
	

	public void validateUpdateRequest(BookShelfDTO request) throws UserServiceException{
		requestBaseValidator.validateRequestDetails(request);
	}

	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
