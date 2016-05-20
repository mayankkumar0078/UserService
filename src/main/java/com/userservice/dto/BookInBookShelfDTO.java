package com.userservice.dto;

import javax.validation.constraints.NotNull;

public class BookInBookShelfDTO extends BaseDTO {
	@NotNull(message="ShelfBookInfo Id can't be null")
	private Integer bookId;
	
	private Boolean isActive;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
