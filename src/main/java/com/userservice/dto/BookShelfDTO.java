package com.userservice.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class BookShelfDTO extends BaseDTO {
	
	@NotNull(message="Book Shelf name can't be null")
    private String bookShelfName;
	
	@NotNull(message="Username can't be null")
	private String username;
	
	private Set<BookInBookShelfDTO> booksInBookShelf;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getBookShelfName() {
		return bookShelfName;
	}

	public void setBookShelfName(String bookShelfName) {
		this.bookShelfName = bookShelfName;
	}

	public Set<BookInBookShelfDTO> getBooksInBookShelf() {
		return booksInBookShelf;
	}

	public void setBooksInBookShelf(Set<BookInBookShelfDTO> booksInBookShelf) {
		this.booksInBookShelf = booksInBookShelf;
	}
	
	
}
