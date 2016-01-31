package com.userservice.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.userservice.entity.BookShelf;

@JsonInclude(Include.NON_NULL)
public class BookShelfResponse extends BaseResponse {
	private List<BookShelf> bookShelves;

	public List<BookShelf> getBookShelves() {
		return bookShelves;
	}

	public void setBookShelves(List<BookShelf> bookShelves) {
		this.bookShelves = bookShelves;
	}
	
	
}
